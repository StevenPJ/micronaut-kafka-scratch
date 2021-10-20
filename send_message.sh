#!/bin/bash

curl -d "{\"key\":$(uuidgen)}" http://localhost:8080/test -H "Content-Type: application/json"
