import request from './request'

export const orderDetail = (orderId, token) => {
  return request({
    url: '/orderline',
    method: 'GET',
    params: {
      orderId: orderId,
      token: token
    }
  })
}
