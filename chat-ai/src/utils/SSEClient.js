import {EventSourcePolyfill} from "event-source-polyfill";


const defaultOptions = {
    retry: 5,
    interval: 60 * 1000,
};

class SSEClient {
    constructor(url, options = defaultOptions) {
        this.url = url;
        this.es = null;
        this.options = options;
        this.retry = options.retry;
        this.timer = null;
    }

    _onOpen() {
        // console.log("server sent event connect created");
    }

    _onMessage(handler) {
        return (event) => {
            this.retry = this.options.retry;
            let payload;

            try {
                payload = JSON.parse(event.data);
                // console.log("receiving data...", payload);
            } catch (error) {
                // console.error("failed to parse payload from server", error);
            }

            if (typeof handler === "function") {
                handler(payload);
            }
        };
    }

    _onError(type, handler) {
        return () => {
            console.error("EventSource connection failed for subscribe.Retry");
            if (this.es) {
                this._removeAllEvent(type, handler);
                this.unsunscribe();
            }

            if (this.retry > 0) {
                this.timer = setTimeout(() => {
                    this.subscribe(type, handler);
                }, this.options.interval);
            } else {
                this.retry--;
            }
        };
    }

    _removeAllEvent(type, handler) {
        this.es.removeEventListener("open", this._onOpen);
        this.es.removeEventListener(type, this._onMessage(handler));
        this.es.removeEventListener("error", this._onError(type, handler));
    }

    subscribe(type, handler,param) {
        this.es = new EventSourcePolyfill(this.url,param);
        this.es.addEventListener("open", this._onOpen);
        this.es.addEventListener(type, this._onMessage(handler));
        this.es.addEventListener("error", this._onError(type, handler));
    }

    unsunscribe() {
        if (this.es) {
            this.es.close();
            this.es = null;
        }
        if (this.timer) {
            clearTimeout(this.timer);
        }
    }
}

export default SSEClient