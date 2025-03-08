import axios from 'axios';
import https from 'https';
import config from '@/config/config'
const qs = require('qs');

const service = axios.create({
  timeout: 10000, // 请求超时时间
  baseURL: config.BASE_API_URL,
  httpsAgent: new https.Agent({
    rejectUnauthorized: false
  }),
  paramsSerializer: params =>
    qs.stringify(params, {
      arrayFormat: 'repeat'
    })
});

// request拦截器
service.interceptors.request.use(
  config => {
    // 如果是put/post请求，用qs.stringify序列化参数
    const isPutPost = config.method === 'put' || config.method === 'post';
    const isJson = config.headers['Content-Type'] === 'application/json';
    const isFile = config.headers['Content-Type'] === 'multipart/form-data';
    if (isPutPost && isJson) {
      config.data = JSON.stringify(config.data);
    }
    if (isPutPost && !isFile && !isJson) {
      config.data = qs.stringify(config.data, {
        arrayFormat: 'repeat'
      });
    }
    return config;
  },
  error => {
    Promise.reject(error);
  }
);

// 响应拦截器
axios.interceptors.response.use(
  response => {
    if (response.status === 200) {
      return Promise.resolve(response);
    } else {
      return Promise.reject(response);
    }
  },
  // 服务器状态码不是200的情况
  error => {
    if (error.response.status) {
      switch (error.response.status) {
        case 404:
          break;
        // 其他错误，直接抛出错误提示
        default:
          // Toast({
          //   message: error.response.data.message,
          //   duration: 1500,
          //   forbidClick: true
          // });
      }
      return Promise.reject(error.response);
    }
  }
);
export const Method = {
  GET: 'get',
  POST: 'post',
  PUT: 'put',
  DELETE: 'delete'
};

export default function request (options) {
  return service(options);
}
