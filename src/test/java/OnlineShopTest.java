import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.PathConstants;
import com.epam.onlineshop.encryption.PasswordEncryption;
import com.epam.onlineshop.entity.types.Type;
import com.epam.onlineshop.exceptions.ProductTechnicalException;
import com.epam.onlineshop.exceptions.RegistrationTechnicalException;
import com.epam.onlineshop.validation.ProductsValidator;
import com.epam.onlineshop.validation.RegistrationValidator;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class OnlineShopTest {
    @Test
    public void fileFoundingTrueTest() {
        File file = new File(PathConstants.OUTGOING_PASSWORDS_TXT);
        boolean flag = file.exists();
        assertTrue(flag);
    }

    @Test
    public void fileFoundingFalseTest() {
        File file = new File("file.txt");
        boolean flag = file.exists();
        assertFalse(flag);
    }

    @Test
    public void identityPasswordTrueTest() {
        String password = "28091995hil";
        String confirmPassword = "28091995hil";
        boolean flag = RegistrationValidator.checkIdentityPasswords(password, confirmPassword);
        assertTrue(flag);
    }

    @Test
    public void identityPasswordFalseTest() {
        String password = "28091995hil";
        String confirmPassword = "28091995";
        boolean flag = RegistrationValidator.checkIdentityPasswords(password, confirmPassword);
        assertFalse(flag);
    }

    @Test
    public void passwordEncryptionTest() {
        String password = "28091995Hil";
        String encryptPassword = PasswordEncryption.encryptMD5(password);
        boolean flag = false;
        if (encryptPassword.length() == 32) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void encryptPasswordIdentity() {
        String password = "28091995Hil";
        String firstEncryptPassword = PasswordEncryption.encryptMD5(password);
        String secondEncryptPassword = PasswordEncryption.encryptMD5(password);
        boolean flag = false;
        if (firstEncryptPassword.equals(secondEncryptPassword)) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void validUserNameTest() throws RegistrationTechnicalException {
        String[] validNames = {
                "Evgeniy Khilabok",
                "Alex",
                "Alexandr Ivanovich Ivanov"
        };
        for (String name : validNames) {
            RegistrationValidator.checkUserName(name);
        }
    }

    @Test(expected = RegistrationTechnicalException.class)
    public void invalidUserNameTest() throws RegistrationTechnicalException {
        String[] invalidNames = {
                "",
                "Evgeniy1995",
                "Alex 96",
                "*John"
        };
        for (String name : invalidNames) {
            RegistrationValidator.checkUserName(name);
        }
    }

    @Test
    public void validEmailTest() throws RegistrationTechnicalException {
        String[] validEmails = {
                "evgen@yandex.ru",
                "evgen-27@yandex.com",
                "evgen.27@yandex.com",
                "evgen111@devcolibri.com",
                "evgen.100@devcolibri.com.ua",
                "evgen@1.com",
                "evgen@gmail.com.com",
                "evgen+27@gmail.com",
                "evgen-27@yandex-test.com"
        };
        for (String email : validEmails) {
            RegistrationValidator.checkEmail(email);
        }
    }

    @Test(expected = RegistrationTechnicalException.class)
    public void invalidEmailTest() throws RegistrationTechnicalException {
        String[] invalidEmails = {
                "",
                "sports-nutrition",
                "evgen@.com.ua",
                "evgen123@gmail.a",
                "evgen123@.com",
                "evgen123@.com.com",
                ".evgen@vk.com",
                "evgen()*@gmail.com",
                "evgen@%*.com",
                "evgen..2013@gmail.com",
                "evgen.@gmail.com",
                "evgen@vk@gmail.com",
                "evgen@gmail.com.1ua"
        };
        for (String email : invalidEmails) {
            RegistrationValidator.checkEmail(email);
        }
    }

    @Test
    public void validPhoneTest() throws RegistrationTechnicalException {
        String validPhone = "375-29-987-44-33";
        RegistrationValidator.checkPhone(validPhone);
    }

    @Test(expected = RegistrationTechnicalException.class)
    public void invalidPhoneTest() throws RegistrationTechnicalException {
        String[] invalidPhones = {
                "37499220099",
                "12345",
                "9990999-99",
                ""
        };
        for (String phone : invalidPhones) {
            RegistrationValidator.checkPhone(phone);
        }
    }

    @Test
    public void validLoginTest() throws RegistrationTechnicalException {
        String[] validLoginArray = {
                "evgen1995",
                "Evgeniy"
        };
        for (String login : validLoginArray) {
            RegistrationValidator.checkLogin(login);
        }
    }

    @Test(expected = RegistrationTechnicalException.class)
    public void invalidLoginTest() throws RegistrationTechnicalException {
        String[] invalidLoginArray = {
                "20Evgen",
                "evgeniy 100",
                "$evgen",
                "EvgeniyKhilabok2809199510011001hilobok",
                ""
        };
        for (String login : invalidLoginArray) {
            RegistrationValidator.checkLogin(login);
        }
    }

    @Test
    public void validPasswordTest() throws RegistrationTechnicalException {
        String[] validPasswords = {
                "28091995Hil",
                "Evgeniy111"
        };
        for (String password : validPasswords) {
            RegistrationValidator.checkPassword(password);
        }
    }

    @Test(expected = RegistrationTechnicalException.class)
    public void invalidPasswordTest() throws RegistrationTechnicalException {
        String[] invalidPasswords = {
                "28091995",
                "12345alex",
                "alex",
                "Evgen",
                ""
        };
        for (String password : invalidPasswords) {
            RegistrationValidator.checkPassword(password);
        }
    }

    @Test
    public void validPathToImageTest() throws ProductTechnicalException {
        String[] validPaths = {
                "img/whey01.png",
                "creatine.jpg",
                "img/catalog/gainer.gif"
        };
        for (String path : validPaths) {
            ProductsValidator.checkPathToImage(path);
        }
    }

    @Test(expected = ProductTechnicalException.class)
    public void invalidPathToImageTest() throws ProductTechnicalException {
        String[] invalidPaths = {
                "whey",
                "creatine1",
                "gainer.mp3"
        };
        for (String path : invalidPaths) {
            ProductsValidator.checkPathToImage(path);
        }
    }

    @Test
    public void typeMethodsCallCheckSample() {
        Type mockedObject = mock(Type.class);
        mockedObject.setId(10);
        mockedObject.setType("value1");
        mockedObject.setTypeDescription("value2");
        verify(mockedObject).setId(10);
        verify(mockedObject).setType("value1");
        verify(mockedObject).setTypeDescription("value2");
    }

    @Test
    public void verifyZeroInteractionsCheck() {
        ICommand mockedObject = mock(ICommand.class);
        verifyZeroInteractions(mockedObject);
    }
}
