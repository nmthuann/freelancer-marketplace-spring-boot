package com.nmt.freelancermarketplacespringboot.services.auth.impl;

import com.nmt.freelancermarketplacespringboot.common.enums.AuthMethodEnum;
import com.nmt.freelancermarketplacespringboot.common.enums.RoleEnum;
import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.users.AuthExceptionMessages;
import com.nmt.freelancermarketplacespringboot.common.utils.JwtServiceUtil;
import com.nmt.freelancermarketplacespringboot.common.utils.MailServiceUtil;
import com.nmt.freelancermarketplacespringboot.dto.Payload;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import com.nmt.freelancermarketplacespringboot.dto.auth.LoginDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterResultDto;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AuthMethodEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.account.RoleEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.services.auth.IAuthService;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAuthMethodService;
import com.nmt.freelancermarketplacespringboot.services.users.account.IRoleService;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AuthService implements IAuthService {

    @Autowired
    JwtServiceUtil jwtService;

    @Autowired
    IAccountService accountService;

    @Autowired
    IAuthMethodService authMethodService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MailServiceUtil mailSender;

//    @Override
//    public CompletableFuture<Tokens> getTokens(Payload payload) {
//        return null;
//    }

    /**
     * using BCrypt to Hash Password in Spring Security 6
     * @param password
     * @return
     */
    @Override
    public String hashPassword(String password) {
       return this.passwordEncoder.encode(password);
    }

    @Override
    public Boolean comparePassword(String password, String storePasswordHash) {
        return this.passwordEncoder.matches(password, storePasswordHash);
    }

    /**
     * using forget password...
     * @param length
     * @param base
     * @return
     */
    @Override
    public String randomPassword(@NotNull int length, @NotNull String base) {
        SecureRandom random = new SecureRandom();
        StringBuilder result = new StringBuilder();

        int baseLength = base.length();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(baseLength);
            result.append(base.charAt(randomIndex));
        }

        return result.toString();
    }

    @Override
    // public CompletableFuture<Tokens> login(LoginDto data) {
    public Tokens login (@NonNull LoginDto data) throws AuthException {

        AccountEntity findAccount = this.accountService.getOneById(data.email());

        if (findAccount != null && findAccount.isStatus()) {
            // fix Hash Pass
            Boolean checkPass = this.comparePassword(data.password(), findAccount.getPassword());

            if (!checkPass) {
                throw new AuthException(AuthExceptionMessages.PASSWORD_WRONG.getMessage());
            }
        } else {
            throw new AuthException(AuthExceptionMessages.LOGIN_INVALID.getMessage());
        }

        // ensure findAccount != null ///assert  findAccount != null;
        return this.jwtService.getTokens(new Payload(
                findAccount.getEmail(),
                findAccount.getRole().getRole_name(),
                findAccount.getSub()
        ));
    }

    /**
     * 1. get email, password
     * -> find acc by email -> [find role, find method] -> generate tokens -> create acc -> create user -> return
     * @param data
     * @return
     */
    @Override
    @Transactional
    public RegisterResultDto register ( RegisterDto data) throws AuthException {

        // step 1: find Acc
        AccountEntity findAccount = this.accountService.getOneById(data.email());
        if (findAccount != null) {
            // email tồn tại
            throw new AuthException(AuthExceptionMessages.EMAIL_EXIST.getMessage());
        }

        // step 2: prepare value for create Account
        try {
            String hashedPassword =
                    this.hashPassword(data.password());

            AuthMethodEntity authMethod =
                    this.authMethodService.getOneById(AuthMethodEnum.LOCAL_AUTHENTICATION.getAuthMethodId());

            RoleEntity role =
                    this.roleService.getOneById(RoleEnum.USER.getRoleId());

            Tokens tokens =
                    this.jwtService.getTokens(new Payload(
                            data.email(),
                            role.getRole_name(),
                            null
                    ));


            AccountEntity newAccount = new AccountEntity();
            newAccount.setEmail(data.email());
            newAccount.setPassword(hashedPassword);
            newAccount.setRefreshToken(tokens.refreshToken());
            newAccount.setStatus(true);
            newAccount.setSub(null);
            newAccount.setAuthMethod(authMethod);
            newAccount.setRole(role);

            // step 3: create Account
            AccountEntity accountCreated = this.accountService.createOne(newAccount);

            UserEntity newUser = new UserEntity();
            newUser.setAccount(accountCreated);
            newUser.setFirstName(data.firstName());
            newUser.setLastName(data.lastName());
            newUser.setBirthday(data.birthday());
            newUser.setGender(data.gender());
            newUser.setPhone(data.phone());
            newUser.setLocation(data.location());

            // create User
            UserEntity userCreated = this.userService.createOne(newUser);

            return new RegisterResultDto(tokens.accessToken(), userCreated.getFirstName());
        }
        catch (Exception ex ){
            System.out.println("ĐĂNG KÝ:::: " + ex.getMessage());
            throw new RuntimeException("REGISTER FAIL");
        }
    }

    @Override
    public String verifyEmail(String email) {
        try {
            AccountEntity checkUser = accountService.getOneById(email);

            if (checkUser != null) {
                throw new AuthException(AuthExceptionMessages.EMAIL_EXIST.getMessage());
            } else {
                String baseString = "0123456789";
                String defaultPassword = randomPassword(8, baseString);

                String subject = "VERIFY EMAIL";
                String content = "Default Password " + ": " + defaultPassword;

                // Send email
                mailSender.sendEmail(email, subject, content);

                // Create account
                // RegisterDto data = new RegisterDto(email, defaultPassword);
                return "SUCCESS"; // Assuming registerEmployee returns a success message
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String changePassword(String email) {
        return "Not Implement changePassword";
    }

    @Override
    public String forgetPassword(String email) {
        return "Not Implement forgetPassword";
    }

    @Override
    public String logout(String email) {
        System.out.println("logout services"+email);
        AccountEntity findAcc = this.accountService.updateRefreshToken(email);
        return "Login Success " + findAcc.getEmail();
    }


}




//
//        return CompletableFuture.supplyAsync(() -> {
//            AccountEntity findAccount = this.accountService.getOneById(data.email());
//
//            if (findAccount != null && findAccount.isStatus()) {
//                Boolean checkPass = this.comparePassword(data.password(), findAccount.getPassword());
//
//                if (!checkPass) {
//                    System.out.println(AuthException.PASSWORD_WRONG);
//                    // throw new InvalidException(AuthException.PASSWORD_WRONG);
//                }
//            } else {
//                System.out.println(AuthException.LOGIN_INVALID);
//                // throw err
//            }
//
//
//            // ensure findAccount != null
//            assert findAccount != null;
//            Tokens tokens = this.jwtService.getTokens(new Payload(
//                    findAccount.getEmail(),
//                    findAccount.getRole().getRole_name(),
//                    findAccount.getSub()
//            ));
//
//            System.out.println("tokens" + tokens);
//
//            return tokens;
//
//        });

//
//return CompletableFuture.supplyAsync(() -> this.accountService.getOneById(data.email()))
//        .thenApply(accountEntity -> {
//        if (accountEntity != null && accountEntity.isStatus()) {
//        throw new UsernameNotFoundException("");
//                }
//                        return accountEntity;
//            })
//                    .thenApplyAsync(accountEntity -> {
//        try {
//String hashedPassword = this.hashPassword(data.password());
//
//AuthMethodEntity authMethod = this.authMethodService.getOneById(AuthMethodEnum.LOCAL_AUTHENTICATION.ordinal());
//RoleEntity role = this.roleService.getOneById(RoleEnum.USER.ordinal());
//
//Tokens tokens = this.jwtService.getTokens(new Payload(
//        data.email(),
//        role.getRole_name(),
//        null
//));
//
//AccountEntity newAccount = new AccountEntity();
//                    newAccount.setEmail(data.email());
//        newAccount.setPassword(hashedPassword);
//                    newAccount.setRefreshToken(tokens.refreshToken());
//        newAccount.setStatus(true);
//                    newAccount.setSub(null);
//                    newAccount.setAuthMethod(authMethod);
//                    newAccount.setRole(role);
//
//AccountEntity accountCreated = this.accountService.createOne(newAccount);
//
//UserEntity newUser = new UserEntity();
//                    newUser.setAccount(accountCreated);
//                    newUser.setFirstName(data.firstName());
//        newUser.setLastName(data.lastName());
//        newUser.setBirthday(data.birthday());
//        newUser.setGender(data.gender());
//        newUser.setPhone(data.phone());
//
//UserEntity userCreated = this.userService.createOne(newUser);
//
//                    return new RegisterResultDto(tokens.accessToken(), userCreated.getFirstName());
//        } catch (Exception ex) {
//        System.out.println("ĐĂNG KÝ:::: " + ex.getMessage());
//        throw new RuntimeException("REGISTER FAIL");
//                }
//                        });



//CompletableFuture<AccountEntity> findAccount =
//        CompletableFuture.supplyAsync(() -> this.accountService.getOneById(data.email()));
//
//        try {
//                if (findAccount.get() != null && findAccount.get().isStatus()) {
//        throw new UsernameNotFoundException("");
//            }
//                    }catch (Exception ex){
//        throw new RuntimeException("");
//        }
//
//
//                try {
//String hashedPassword = this.hashPassword(data.password());
//// Create account
//
//
//
//AuthMethodEntity authMethod =
//        this.authMethodService.getOneById(AuthMethodEnum.LOCAL_AUTHENTICATION.ordinal());
//
//RoleEntity role =
//        this.roleService.getOneById(RoleEnum.USER.ordinal());
//
//Tokens tokens = this.jwtService.getTokens(new Payload(
//        data.email(),
//        role.getRole_name(),
//        null
//));
//
//
//AccountEntity newAccount = new AccountEntity();
//            newAccount.setEmail(data.email());
//        newAccount.setPassword(hashedPassword);
//            newAccount.setRefreshToken(tokens.refreshToken());
//        newAccount.setStatus(true);
//            newAccount.setSub(null);
//            newAccount.setAuthMethod(authMethod);
//            newAccount.setRole(role);
//
//
//AccountEntity accountCreated = this.accountService.createOne(newAccount);
//
//
//
//UserEntity newUser = new UserEntity();
//            newUser.setAccount(accountCreated);
//            newUser.setFirstName(data.firstName());
//        newUser.setLastName(data.lastName());
//        newUser.setBirthday(data.birthday());
//        newUser.setGender(data.gender());
//        newUser.setPhone(data.phone());
//
//UserEntity userCreated = this.userService.createOne(newUser);
//
//            return CompletableFuture.completedFuture(
//                    new RegisterResultDto(tokens.accessToken(), userCreated.getFirstName()));
//        }
//        catch (Exception ex) {
//        System.out.println("ĐĂNG KÝ:::: " + ex.getMessage());
//        throw new RuntimeException("REGISTER FAIL");
//        }