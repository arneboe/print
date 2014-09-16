from urllib.request import urlopen
import json

def words():
    url = "http://www.pornmd.com/getliveterms"
    response = urlopen(url)

    data = json.loads(response.read().decode("UTF-8")) #is a list of dicts
    for d in data:
        print(d["keyword"])



if __name__ == "__main__":
    words()


