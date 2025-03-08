export default {
    WEBSITE_NAME: process.env.VUE_APP_WEBSITE_NAME || "聊天",
    BASE_API_URL: process.env.VUE_APP_API_BASE_URL || "",
    BASE_WS_URL: process.env.VUE_APP_WEB_SOCKET_URL || "",
    API_SOCKET_URL:process.env.VUE_APP_API_SOCKET_URL ||"",
    BASE_COMMON: process.env.VUE_APP_COMMON || "",
    BASE_ACTIVE:process.env.VUE_APP_API_ACTIVE_URL||"",
};

