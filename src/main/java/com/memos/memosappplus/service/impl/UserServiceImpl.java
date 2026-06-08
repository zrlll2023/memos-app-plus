package com.memos.memosappplus.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memos.memosappplus.dto.UserLoginDTO;
import com.memos.memosappplus.dto.UserRegisterDTO;
import com.memos.memosappplus.entity.User;
import com.memos.memosappplus.mapper.UserMapper;
import com.memos.memosappplus.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService { // 履行UserService的行为

    // 注册
    @Override
    public void register(UserRegisterDTO dto) {
        // 第一步：检查用户名是否已存在
        User existUser = this.lambdaQuery()
                .eq(User::getUsername, dto.getUsername())
                .one();

        if (existUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        // 第二步：密码加密
        String encodedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());

        // 第三步：存入数据库
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encodedPassword);
        this.save(user);  // IService 提供的保存方法
    }

    //登录
    @Override
    public String login(UserLoginDTO dto) {
        // 第一步：查询用户名是否存在
        User existUser = this.lambdaQuery()
                .eq(User::getUsername, dto.getUsername())
                .one();
        if (existUser != null /* 如果用户名已存在 */ ) {

            // 第二步：验证密码
            // BCrypt.checkpw(String plaintext, String hashed)
            // 这行是方法的定义格式，在实际调用时是不需要写出来的
            boolean isPasswordMatch = BCrypt.checkpw(dto.getPassword(), existUser.getPassword());
            // isPasswordMatch 是一个我们自己定义的 布尔类型（boolean）变量 🏷️。它的名字是自定义的，意思是“密码是否匹配”
            // true：代表用户输入的密码与数据库里的密文吻合（登录成功）。
            // false：代表密码不吻合（登录失败）。
            if ( isPasswordMatch ){//登陆成功
            // isPasswordMatch 本身就是一个布尔值（true 或 false），if 语句会自动对它进行判断，不需要再多此一举地去和 true 做比较。
                // 第三步：生成 JWT Token。假设有一个工具类叫 JwtUtils，传入用户ID和用户名来生成 Token
                // 模板 ：String token = JwtUtils.createToken(existUser.getId(), existUser.getUsername());
                String token = JWT.create()
                        // withClaim：可以用来存放你想放入 Token 的用户信息
                        .withClaim("userId", existUser.getId()) //  把用户ID存入 Token
                        .withClaim("username", existUser.getUsername()) //  把用户名存入 Token
                        // 加密算法，里面的参数是自定义的“密钥”（一串复杂的字符串，用来保证 Token 不被别人篡改）
                        .sign(Algorithm.HMAC256("你的密钥")); //  签名加密
                /*
                token是经过：算法信息（这里用的是HMAC256的算法信息） + .withClaim里面的内容 + 你的密钥（也可以是任何内容，统称为密钥）经过 算法计算出来的
                 */

                // 第四步：返回 Token
                return token;

            }
            throw new RuntimeException("密码错误");
        }
        throw new RuntimeException("用户名不存在");





    }

}


