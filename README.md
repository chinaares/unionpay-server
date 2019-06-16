
### api list

#### 创建订单

* /order
* http method: post
* request 

param | name | type | required | desc
-------- | ---| ---| -----------|-----
money | 订单金额 | string | y | 真实的订单金额

* response

param | name | type  | desc
-------- | ---| ---| -------
orderSn | 订单号 | string | 系统自己生成的订单号
money | 订单金额 | double | 真实的订单金额
randomMoney | 随机金额| double | 用于验证
status | 订单状态 | string| CREATED,PAID,EXPIRED
createTime| 创建时间 | long | 时间戳
updateTime| 修改时间 | long | 时间戳
expireTime| 过期时间 | long | 时间戳
_id | id |string | 唯一主键id