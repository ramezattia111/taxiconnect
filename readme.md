### Setup Instructions

#### PROJECT STRUCTURE
```
└── 📁taxiconnect-main
    └── 📁src/📁main/📁java/📁com
                    └── 📁taxiconnect
                        └── 📁controllers
                        └── 📁entities
                            └── 📁roles
                            └── 📁user
                                └── 📁dtos
                        └── 📁exceptions
                        └── 📁repo
                        └── 📁services
                        └── TaxiconnectApplication.java
            └── 📁resources
                └── application.properties
        └── 📁test
    └── .gitattributes
    └── .gitignore
    └── docker-compose.yaml
    └── mvnw
    └── mvnw.cmd
    └── pom.xml
    └── readme.md
    └── stylesheet.txt
```

#### 1. Clone the Repository
````bash
git clone https://github.com/ramezattia111/taxiconnect.git
cd taxiconnect
````

#### 2. Run docker compose
````bash
docker compose up --build --detach
````
