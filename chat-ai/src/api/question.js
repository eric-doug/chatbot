import request, {Method} from "@/utils/request";

export function sendQuestion (data) {
    return request({
        url: `/question/ask`,
        method: Method.POST,
        needToken: true,
        headers: {'Content-Type': 'application/json'},
        data
    });
}

export function getContextById (data) {
    return request({
        url: `/question/getContextById/${data}`,
        method: Method.POST,
        needToken: true,
        headers: {'Content-Type': 'application/json'},
        data
    });
}


export function getMessageById (data) {
    return request({
        url: `/question/getMessage/${data}`,
        method: Method.POST,
        needToken: true,
        headers: {'Content-Type': 'application/json'},
        data
    });
}

export function deleteById (data) {
    return request({
        url: `/question/deleteById/${data}`,
        method: Method.GET,
        needToken: true,
        // headers: {'Content-Type': 'application/json'},
    });
}

