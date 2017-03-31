package com.contrastsecurity.tools.forensics.mysql;

import org.owasp.esapi.Authenticator;
import org.owasp.esapi.User;
import org.owasp.esapi.errors.AuthenticationException;
import org.owasp.esapi.errors.EncryptionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class NoopAuthenticator implements Authenticator {
    /**
     * Clears the current User. This allows the thread to be reused safely.
     * <p/>
     * This clears all threadlocal variables from the thread. This should ONLY
     * be called after all possible ESAPI operations have concluded. If you
     * clear too early, many calls will fail, including logging, which requires
     * the user identity.
     */
    @Override
    public void clearCurrent() {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    /**
     * Calls login with the *current* request and response.
     *
     * @return Authenticated {@code User} if login is successful.
     * @see org.owasp.esapi.HTTPUtilities#setCurrentHTTP(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public User login() throws AuthenticationException {
        return null; // To change body of implemented methods use File |
                     // Settings | File Templates.
    }

    /**
     * This method should be called for every HTTP request, to login the current
     * user either from the session of HTTP request. This method will set the
     * current user so that getCurrentUser() will work properly.
     * <p/>
     * Authenticates the user's credentials from the HttpServletRequest if
     * necessary, creates a session if necessary, and sets the user as the
     * current user.
     * <p/>
     * Specification: The implementation should do the following: 1) Check if
     * the User is already stored in the session a. If so, check that session
     * absolute and inactivity timeout have not expired b. Step 2 may not be
     * required if 1a has been satisfied 2) Verify User credentials a. It is
     * recommended that you use loginWithUsernameAndPassword(HttpServletRequest,
     * HttpServletResponse) to verify credentials 3) Set the last host of the
     * User (ex. user.setLastHostAddress(address) ) 4) Verify that the request
     * is secure (ex. over SSL) 5) Verify the User account is allowed to be
     * logged in a. Verify the User is not disabled, expired or locked 6) Assign
     * User to session variable
     *
     * @param request
     *            the current HTTP request
     * @param response
     *            the HTTP response
     * @return the User
     * @throws org.owasp.esapi.errors.AuthenticationException
     *             if the credentials are not verified, or if the account is
     *             disabled, locked, expired, or timed out
     */
    @Override
    public User login(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return null; // To change body of implemented methods use File |
                     // Settings | File Templates.
    }

    /**
     * Verify that the supplied password matches the password for this user.
     * Password should be stored as a hash. It is recommended you use the
     * hashPassword(password, accountName) method in this class. This method is
     * typically used for "reauthentication" for the most sensitive functions,
     * such as transactions, changing email address, and changing other account
     * information.
     *
     * @param user
     *            the user who requires verification
     * @param password
     *            the hashed user-supplied password
     * @return true, if the password is correct for the specified user
     */
    @Override
    public boolean verifyPassword(User user, String password) {
        return false; // To change body of implemented methods use File |
                      // Settings | File Templates.
    }

    /**
     * Logs out the current user.
     * <p/>
     * This is usually done by calling User.logout on the current User.
     */
    @Override
    public void logout() {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    /**
     * Creates a new User with the information provided. Implementations should
     * check accountName and password for proper format and strength against
     * brute force attacks ( verifyAccountNameStrength(String),
     * verifyPasswordStrength(String, String) ).
     * <p/>
     * Two copies of the new password are required to encourage user interface
     * designers to include a "re-type password" field in their forms.
     * Implementations should verify that both are the same.
     *
     * @param accountName
     *            the account name of the new user
     * @param password1
     *            the password of the new user
     * @param password2
     *            the password of the new user. This field is to encourage user
     *            interface designers to include two password fields in their
     *            forms.
     * @return the User that has been created
     * @throws org.owasp.esapi.errors.AuthenticationException
     *             if user creation fails due to any of the qualifications
     *             listed in this method's description
     */
    @Override
    public User createUser(String accountName, String password1, String password2) throws AuthenticationException {
        return null; // To change body of implemented methods use File |
                     // Settings | File Templates.
    }

    /**
     * Generate a strong password. Implementations should use a large character
     * set that does not include confusing characters, such as i I 1 l 0 o and
     * O. There are many algorithms to generate strong memorable passwords that
     * have been studied in the past.
     *
     * @return a password with strong password strength
     */
    @Override
    public String generateStrongPassword() {
        return null; // To change body of implemented methods use File |
                     // Settings | File Templates.
    }

    /**
     * Generate strong password that takes into account the user's information
     * and old password. Implementations should verify that the new password
     * does not include information such as the username, fragments of the old
     * password, and other information that could be used to weaken the strength
     * of the password.
     *
     * @param user
     *            the user whose information to use when generating password
     * @param oldPassword
     *            the old password to use when verifying strength of new
     *            password. The new password may be checked for fragments of
     *            oldPassword.
     * @return a password with strong password strength
     */
    @Override
    public String generateStrongPassword(User user, String oldPassword) {
        return null; // To change body of implemented methods use File |
                     // Settings | File Templates.
    }

    /**
     * Changes the password for the specified user. This requires the current
     * password, as well as the password to replace it with. The new password
     * should be checked against old hashes to be sure the new password does not
     * closely resemble or equal any recent passwords for that User. Password
     * strength should also be verified. This new password must be repeated to
     * ensure that the user has typed it in correctly.
     *
     * @param user
     *            the user to change the password for
     * @param currentPassword
     *            the current password for the specified user
     * @param newPassword
     *            the new password to use
     * @param newPassword2
     *            a verification copy of the new password
     * @throws org.owasp.esapi.errors.AuthenticationException
     *             if any errors occur
     */
    @Override
    public void changePassword(User user, String currentPassword, String newPassword, String newPassword2)
            throws AuthenticationException {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    /**
     * Returns the User matching the provided accountId. If the accoundId is not
     * found, an Anonymous User or null may be returned.
     *
     * @param accountId
     *            the account id
     * @return the matching User object, or the Anonymous User if no match
     *         exists
     */
    @Override
    public User getUser(long accountId) {
        return null; // To change body of implemented methods use File |
                     // Settings | File Templates.
    }

    /**
     * Returns the User matching the provided accountName. If the accoundId is
     * not found, an Anonymous User or null may be returned.
     *
     * @param accountName
     *            the account name
     * @return the matching User object, or the Anonymous User if no match
     *         exists
     */
    @Override
    public User getUser(String accountName) {
        return null; // To change body of implemented methods use File |
                     // Settings | File Templates.
    }

    /**
     * Gets a collection containing all the existing user names.
     *
     * @return a set of all user names
     */
    @Override
    public Set getUserNames() {
        return null; // To change body of implemented methods use File |
                     // Settings | File Templates.
    }

    /**
     * Returns the currently logged in User.
     *
     * @return the matching User object, or the Anonymous User if no match
     *         exists
     */
    @Override
    public User getCurrentUser() {
        return null; // To change body of implemented methods use File |
                     // Settings | File Templates.
    }

    /**
     * Sets the currently logged in User.
     *
     * @param user
     *            the user to set as the current user
     */
    @Override
    public void setCurrentUser(User user) {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    /**
     * Returns a string representation of the hashed password, using the
     * accountName as the salt. The salt helps to prevent against "rainbow"
     * table attacks where the attacker pre-calculates hashes for known strings.
     * This method specifies the use of the user's account name as the "salt"
     * value. The Encryptor.hash method can be used if a different salt is
     * required.
     *
     * @param password
     *            the password to hash
     * @param accountName
     *            the account name to use as the salt
     * @return the hashed password
     * @throws org.owasp.esapi.errors.EncryptionException
     *
     */
    @Override
    public String hashPassword(String password, String accountName) throws EncryptionException {
        return null; // To change body of implemented methods use File |
                     // Settings | File Templates.
    }

    /**
     * Removes the account of the specified accountName.
     *
     * @param accountName
     *            the account name to remove
     * @throws org.owasp.esapi.errors.AuthenticationException
     *             the authentication exception if user does not exist
     */
    @Override
    public void removeUser(String accountName) throws AuthenticationException {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    /**
     * Ensures that the account name passes site-specific complexity
     * requirements, like minimum length.
     *
     * @param accountName
     *            the account name
     * @throws org.owasp.esapi.errors.AuthenticationException
     *             if account name does not meet complexity requirements
     */
    @Override
    public void verifyAccountNameStrength(String accountName) throws AuthenticationException {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    /**
     * Ensures that the password meets site-specific complexity requirements,
     * like length or number of character sets. This method takes the old
     * password so that the algorithm can analyze the new password to see if it
     * is too similar to the old password. Note that this has to be invoked when
     * the user has entered the old password, as the list of old credentials
     * stored by ESAPI is all hashed. Additionally, the user object is taken in
     * order to verify the password and account name differ.
     *
     * @param oldPassword
     *            the old password
     * @param newPassword
     *            the new password
     * @param user
     *            the user
     * @throws org.owasp.esapi.errors.AuthenticationException
     *             if newPassword is too similar to oldPassword or if
     *             newPassword does not meet complexity requirements
     */
    @Override
    public void verifyPasswordStrength(String oldPassword, String newPassword, User user)
            throws AuthenticationException {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    /**
     * Determine if the account exists.
     *
     * @param accountName
     *            the account name
     * @return true, if the account exists
     */
    @Override
    public boolean exists(String accountName) {
        return false; // To change body of implemented methods use File |
                      // Settings | File Templates.
    }
}
