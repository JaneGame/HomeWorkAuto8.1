package ru.netology.test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.Login;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Forms {

    @AfterAll

    public static void deleteInfo() {
    DataHelper help = new DataHelper();
    help.deleteInfo();}


        @Test
        public void formTest() {

            var login = open("http://localhost:9999", Login.class);
            var authInfo = DataHelper.getAuthInfo();
            var verificationPage = login.validLogin(authInfo);
            var verificationCode = DataHelper.getValidVerificationCode();
            verificationPage.validVerify(verificationCode);

        }

    @Test
    public void formTestLoginNegative() {

        var login = open("http://localhost:9999", Login.class);
        var authInfo = DataHelper.getErrorAuthInfo();
        login.invalidLogin(authInfo);
        login.invalidLogin(authInfo);
        login.invalidLogin(authInfo);
        $(withText("Пользователь заблокирован")).shouldBe(visible);


    }
    }

