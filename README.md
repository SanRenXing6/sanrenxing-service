# SanRenXing Service
Backend service for SanRenXing app

## How to start
1. Install dependencies: 

    ```mvn clean install```

2. Set up container database:

    ```docker run --name <name> -e POSTGRES_PASSWORD=<password> -d -p 5432:5432 postgres:alpine```

    ```docker exec -it <container-id> bin/bash```

    ```CREATE DATABASE <database-name>;```

3. Start project: run `ServiceApplication.java`

4. Try to make api requests. For example, go to "http://localhost:8080/api/v1/users" to fetch all the users data



