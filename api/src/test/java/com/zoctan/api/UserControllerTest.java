package com.zoctan.api;

import com.zoctan.api.util.RSAUtil;
import org.junit.Assert;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

@SuppressWarnings("FieldCanBeLocal")
public class UserControllerTest extends BaseControllerTest {

    private final String resource = "/user";

    /**
     * MockMvc 测试 SpringSecurity
     * 可以参考官网 https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/
     * 带上 JWT 测试可以参考 stackoverflow
     * https://stackoverflow.com/questions/29510759/how-to-test-spring-security-oauth2-resource-server-security
     */
    @Test(timeout = 5000)
    public void login() throws Exception {
        /*this.mockMvc.perform(post(this.url + this.resource + "/login")
                        .param("username", "admin")
                        .param("password", "admin"));
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();*/
        RSAUtil rsaUtil = new RSAUtil();
        final PublicKey publicKey = rsaUtil.loadPemPublicKey("rsa/public-key.pem");
        final PrivateKey privateKey = rsaUtil.loadPemPrivateKey("rsa/private-key.pem");
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);
    }
}