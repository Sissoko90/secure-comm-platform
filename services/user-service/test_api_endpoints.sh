#!/bin/bash

BASE_URL="http://localhost:8090/api"

# Test création administration
curl -X POST "$BASE_URL/administrations" -H "Content-Type: application/json" -d '{
  "name": "Finance Administration"
}'

echo -e "\n"

# Test récupération administrations
curl -X GET "$BASE_URL/administrations"

echo -e "\n"

# Test mise à jour administration
curl -X PUT "$BASE_URL/administrations/1" -H "Content-Type: application/json" -d '{
  "name": "Updated Finance Administration"
}'

echo -e "\n"

# Test suppression administration
curl -X DELETE "$BASE_URL/administrations/1"

echo -e "\n"

# Test création département
curl -X POST "$BASE_URL/departments" -H "Content-Type: application/json" -d '{
  "name": "IT Department",
  "administrationId": 1
}'

echo -e "\n"

# Test récupération départements
curl -X GET "$BASE_URL/departments"

echo -e "\n"

# Test mise à jour département
curl -X PUT "$BASE_URL/departments/1" -H "Content-Type: application/json" -d '{
  "name": "Updated IT Department",
  "administrationId": 1
}'

echo -e "\n"

# Test suppression département
curl -X DELETE "$BASE_URL/departments/1"

echo -e "\n"

# Test création utilisateur
curl -X POST "$BASE_URL/users" -H "Content-Type: application/json" -d '{
  "username": "jdoe",
  "fullName": "John Doe",
  "role": "USER",
  "administrationId": 1,
  "departmentId": 1
}'

echo -e "\n"

# Test récupération utilisateurs par administration
curl -X GET "$BASE_URL/users/administration/1?page=0&size=10"

echo -e "\n"

# Test récupération utilisateurs par département
curl -X GET "$BASE_URL/users/department/1?page=0&size=10"

echo -e "\n"

# Test recherche utilisateurs
curl -X GET "$BASE_URL/users/search?username=jdoe&administrationId=1&departmentId=1&role=USER&page=0&size=10"

echo -e "\n"

# Test mise à jour utilisateur
curl -X PUT "$BASE_URL/users/1" -H "Content-Type: application/json" -d '{
  "username": "jdoe_updated",
  "fullName": "John Doe Updated",
  "role": "ADMIN",
  "administrationId": 1,
  "departmentId": 1
}'

echo -e "\n"

# Test suppression utilisateur
curl -X DELETE "$BASE_URL/users/1"

echo -e "\n"
