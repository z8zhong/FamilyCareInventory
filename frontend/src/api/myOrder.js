import request from './request'

export const myOrders = (token) => {
  return request({
    url: '/order',
    method: 'GET',
    params: {
      token: token
    }
  })
}
