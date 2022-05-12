import rrr from './request'

export const login = (data) => {
  return rrr({
    url: '/login',
    method: 'POST',
    data
  })
}
