import request, {Method} from "@/utils/request";

export function chat (data) {
    return request({
        url: `/chat`,
        method: Method.POST,
        needToken: false,
        headers: {'Content-Type': 'application/json',"uid":data.uid},
        data
    })
}

export function closeChat (data) {
    return request({
        url: `/closeSse`,
        method: Method.GET,
        needToken: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded',"uid":data.uid},
    })
}