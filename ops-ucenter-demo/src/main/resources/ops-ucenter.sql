CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `series` varchar(64) NOT NULL COMMENT '用户登录成功后获取的UUID值，客户端保存的cookie记录是EncryptionUtil.base64Encode(series)',
  `token` varchar(64) NOT NULL COMMENT '用于校验能否登录的密文，其加密方式是EncryptionUtil.sha256Hex(用户名_密码_自动登录失效的时间点的字符串_自定义的salt)',
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '自动登录失效的时间',
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用于校验用户自动登录的表';

