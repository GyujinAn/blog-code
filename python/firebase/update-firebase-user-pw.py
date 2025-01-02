import argparse
import firebase_admin
from firebase_admin import auth


def update_user_password(email, password):
    # initialize the Firebase Admin SDK
    cred = firebase_admin.credentials.Certificate('')
    firebase_admin.initialize_app(cred)

    # get the user by their email address
    user = auth.get_user_by_email(email)

    # update the user's password
    auth.update_user(user.uid, password=password)


if __name__ == '__main__':
    # create an ArgumentParser object
    parser = argparse.ArgumentParser(description='Update user password')

    # add arguments
    parser.add_argument('-e', '--email', type=str, required=True,
                        help='Email address of the user')
    parser.add_argument('-p', '--password', type=str, required=True,
                        help='New password for the user')

    # parse the arguments
    args = parser.parse_args()

    # call the update_user_password function with the parsed arguments
    update_user_password(args.email, args.password)
