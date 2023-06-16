import firebase_admin
from firebase_admin import credentials
from firebase_admin import auth

# Initialize the Firebase Admin SDK with your project credentials
cred = credentials.Certificate("path/to/serviceAccountKey.json")
firebase_admin.initialize_app(cred)

# Get all users
all_users = auth.list_users()

# Iterate over each user and delete them
for user in all_users.users:
    auth.delete_user(user.uid)
    print("Deleted user:", user.uid)

print("All users have been deleted.")
