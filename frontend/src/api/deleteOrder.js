import request from './request'

export const deleteOrder = (data) => {
  return request({
    url: '/order',
    method: 'DELETE',
    params: {
      token: data.token,
      orderId: data.orderId
    }
  })
}
