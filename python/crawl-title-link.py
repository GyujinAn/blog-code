import requests
from bs4 import BeautifulSoup

# URL of the webpage to fetch
url = "https://thebell.co.kr/free/content/Article.asp?svccode=00"

# Fetch the webpage content
response = requests.get(url)
response.raise_for_status()  # This will raise an exception for HTTP errors

# Parse the HTML content
soup = BeautifulSoup(response.text, 'html.parser')

# Find all <a> tags and extract href and title
data = []
for link in soup.find_all('a', title=True):  # Ensuring the title attribute exists
    href = link.get('href')
    title = link.get('title')
    if href and title:
        data.append((title, href))
        print(f"Title: {title}, Href: {href}")

# Optionally, you could write these to a file or handle them as needed
