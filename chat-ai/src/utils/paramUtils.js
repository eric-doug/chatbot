export function converToUrl (requestParams){
    let params = [];

    Object.entries(requestParams).forEach(([key,value])=>{
        let param = key+ '=' +value;
        if(value){
            params.push(param)
        }

    })
    return '?' +params.join('&')
}
