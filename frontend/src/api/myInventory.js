import request from './request'

export const myInventory = (token) => {
  return request({
    url: '/product',
    method: 'GET',
    params: {
      token: token
    }
  })
}
