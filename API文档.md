# NoteBook API 文档

## 基础信息

- 基础URL: `/api`


## 统一响应格式

所有API响应均使用以下统一格式：

```json
{
  "code": 200,       // 状态码
  "message": "操作成功", // 响应消息
  "data": {},        // 响应数据，可能为null
  "success": true,   // 是否成功
  "timestamp": 1628756982000 // 时间戳
}
```

## 认证接口

### 用户登录

- **URL**: `/api/auth/login`
- **方法**: POST
- **描述**: 用户登录接口

#### 请求参数

```json
{
  "username": "用户名",  // 必填
  "password": "密码"    // 必填
}
```

#### 响应示例

成功响应：

```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "id": 1,
    "username": "用户名",
    "email": "用户邮箱",
    "token": "JWT认证令牌"
  },
  "success": true,
  "timestamp": 1628756982000
}
```

失败响应：

```json
{
  "code": 2001,
  "message": "用户名或密码错误",
  "data": null,
  "success": false,
  "timestamp": 1628756982000
}
```

### 用户注册

- **URL**: `/api/auth/register`
- **方法**: POST
- **描述**: 用户注册接口

#### 请求参数

```json
{
  "username": "用户名",       // 必填，长度4-20个字符
  "password": "密码",        // 必填，长度6-20个字符
  "confirmPassword": "确认密码", // 必填，必须与密码一致
  "email": "邮箱"           // 必填，必须是有效的邮箱格式
}
```

#### 响应示例

成功响应：

```json
{
  "code": 200,
  "message": "注册成功",
  "data": {
    "id": 1,
    "username": "用户名",
    "email": "用户邮箱",
    "token": "JWT认证令牌"
  },
  "success": true,
  "timestamp": 1628756982000
}
```

失败响应：

```json
{
  "code": 2004,
  "message": "用户名已存在",
  "data": null,
  "success": false,
  "timestamp": 1628756982000
}
```

## 笔记接口

> 注意：所有笔记接口都需要在请求头中携带JWT令牌进行认证

### 获取笔记列表

- **URL**: `/api/notes`
- **方法**: GET
- **描述**: 获取当前登录用户的所有笔记列表

#### 请求头

```
Authorization: Bearer {token}
```

#### 响应示例

成功响应：

```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "userId": 1,
      "title": "笔记标题",
      "content": "笔记内容",
      "status": "published",
      "createdAt": "2023-08-12T10:30:00",
      "updatedAt": "2023-08-12T10:30:00"
    },
    {
      "id": 2,
      "userId": 1,
      "title": "笔记标题2",
      "content": "笔记内容2",
      "status": "draft",
      "createdAt": "2023-08-12T11:30:00",
      "updatedAt": "2023-08-12T11:30:00"
    }
  ],
  "success": true,
  "timestamp": 1628756982000
}
```

失败响应：

```json
{
  "code": 2000,
  "message": "用户未登录",
  "data": null,
  "success": false,
  "timestamp": 1628756982000
}
```

### 获取笔记详情

- **URL**: `/api/notes/{id}`
- **方法**: GET
- **描述**: 根据ID获取笔记详情

#### 请求头

```
Authorization: Bearer {token}
```

#### 路径参数

| 参数名 | 类型 | 描述 |
| ------ | ---- | ---- |
| id | Long | 笔记ID |

#### 响应示例

成功响应：

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "userId": 1,
    "title": "笔记标题",
    "content": "笔记内容",
    "status": "published",
    "createdAt": "2023-08-12T10:30:00",
    "updatedAt": "2023-08-12T10:30:00"
  },
  "success": true,
  "timestamp": 1628756982000
}
```

失败响应：

```json
{
  "code": 5000,
  "message": "笔记不存在",
  "data": null,
  "success": false,
  "timestamp": 1628756982000
}
```

### 创建笔记

- **URL**: `/api/notes`
- **方法**: POST
- **描述**: 创建新笔记

#### 请求头

```
Authorization: Bearer {token}
```

#### 请求参数

```json
{
  "title": "笔记标题",    // 必填，最大长度100个字符
  "content": "笔记内容",  // 必填
  "status": "published"  // 可选，默认为"published"，可选值："draft"或"published"
}
```

#### 响应示例

成功响应：

```json
{
  "code": 200,
  "message": "创建成功",
  "data": {
    "id": 1,
    "userId": 1,
    "title": "笔记标题",
    "content": "笔记内容",
    "status": "published",
    "createdAt": "2023-08-12T10:30:00",
    "updatedAt": "2023-08-12T10:30:00"
  },
  "success": true,
  "timestamp": 1628756982000
}
```

失败响应：

```json
{
  "code": 1000,
  "message": "参数错误",
  "data": {
    "title": "标题不能为空",
    "content": "内容不能为空"
  },
  "success": false,
  "timestamp": 1628756982000
}
```

### 删除笔记

- **URL**: `/api/notes/{id}`
- **方法**: DELETE
- **描述**: 根据ID删除笔记

#### 请求头

```
Authorization: Bearer {token}
```

#### 路径参数

| 参数名 | 类型 | 描述 |
| ------ | ---- | ---- |
| id | Long | 笔记ID |

#### 响应示例

成功响应：

```json
{
  "code": 200,
  "message": "删除成功",
  "data": null,
  "success": true,
  "timestamp": 1628756982000
}
```

失败响应：

```json
{
  "code": 7003,
  "message": "无权删除该笔记",
  "data": null,
  "success": false,
  "timestamp": 1628756982000
}
```
