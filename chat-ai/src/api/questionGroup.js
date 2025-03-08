import request, {Method} from "@/utils/request";
import {converToUrl} from "@/utils/paramUtils";

export function getGroup (data) {
    return request({
        url: `/questionGroup/getGroup`,
        method: Method.POST,
        needToken: true,
        headers: {'Content-Type': 'application/json'},
        data
    });
}
export function getListByPage (data) {
    return request({
        url: `/questionGroup/getListByPage`+converToUrl(data),
        method: Method.GET,
        needToken: true,
        headers: {'Content-Type': 'application/json'},
    });
}

export function deleteGroup (data) {
    return request({
        url: `/questionGroup/deleteById/${data}`,
        method: Method.DELETE,
        needToken: true
    })
}


export function updateById (data) {
    return request({
        url: `/questionGroup/updateById`,
        method: Method.POST,
        needToken: true,
        headers: {'Content-Type': 'application/json'},
        data
    })
}
export function saveGroup (data) {
    return request({
        url: `/questionGroup/save`,
        method: Method.POST,
        needToken: true,
        headers: {'Content-Type': 'application/json'},
        data
    })
}



