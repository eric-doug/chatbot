
const ROOM_URL = "ROOM_URL"

const ROOM_INFO = "ROOM_INFO"

const DY_COOKIES = "DY_COOKIES"

const DEIVCE_INFO='DEIVCE_INFO'

const CHAT_INFO='CHAT_INFO'

export function setRoomUrl(state) {
    localStorage.setItem(ROOM_URL, JSON.stringify(state))
}

export function getRoomUrl() {
    const data = localStorage.getItem(ROOM_URL)
    return data ? JSON.parse(data) : '{}'
}

export function setRoomInfo(state) {
    localStorage.setItem(ROOM_INFO, JSON.stringify(state))
}

export function getRoomInfo() {
    const data = localStorage.getItem(ROOM_INFO)
    return data ? JSON.parse(data) : '{}'
}


export function setDyCookies(state) {
    localStorage.setItem(DY_COOKIES, JSON.stringify(state))
}

export function getDyCookies() {
    const data = localStorage.getItem(DY_COOKIES)
    return data ? JSON.parse(data) : '{}'
}
export function removeDyCookies() {
    localStorage.removeItem(DY_COOKIES)
}

/**
 * 存储本地是否是H5
 */
export function getDeviceInfo() {
    const data = localStorage.getItem(DEIVCE_INFO)
    return data ? JSON.parse(data) : '{}'
}

/**
 * 存储本地订单信息
 */
export function setDeviceInfo(state) {
    localStorage.setItem(DEIVCE_INFO, JSON.stringify(state))
}

export function setChatInfo(state) {
    localStorage.setItem(CHAT_INFO, JSON.stringify(state))
}

export function getChatInfo() {
    const data = localStorage.getItem(CHAT_INFO)
    return data ? JSON.parse(data) : '{}'
}
