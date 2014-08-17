rotmg-purchaser
===============

Small program to send multiple request to purchase a package in the game "Realm of the Mad God".


Usage
===============

Edit src/MultiplePackagePurchaser.java with your login credentials.

1. Change `packageId` to the packageId you'd like to attempt to purchase multiple times. (`377` is the free backback package, for instance).
2. Change `yourEmail` to your email associated with your RotMG account.
3. Change `yourPassword` to your password associated with your RotMG account.


Troubleshooting
===============

* `<Error>Account not found</Error>`
The email/password combination you used is incorrect. Ensure the email and password is correct.
* `<Error>WebLoginDialig.passwordResetBlock</Error>`
The account you provided still needs its password reset from the Swatsec incident.
* `Success Thread: xx`
Each one of these lines you see equates to one successful package purchase.

I will update the troubleshooting section as needed, when/if necessary.
