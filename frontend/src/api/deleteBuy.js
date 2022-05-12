import request from './request'

export const deleteBuy = (data) => {
  return request({
    url: '/buy',
    method: 'DELETE',
    params: {
      token: data.token,
      buyId: data.buyId
    }
  })
}
