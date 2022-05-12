import request from './request'

export const buyDetail = (buyId, token) => {
  return request({
    url: '/buyline',
    method: 'GET',
    params: {
      buyId: buyId,
      token: token
    }
  })
}
