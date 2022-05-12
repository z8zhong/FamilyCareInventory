import request from './request'

export const deleteCost = (data) => {
  return request({
    url: '/cost',
    method: 'DELETE',
    params: {
      token: data.token,
      costId: data.costId
    }
  })
}
