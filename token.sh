#!/bin/bash

curl -u admin:tuG28kCMie -XPOST "http://35.224.254.203:8083/artifactory/api/security/token" -d "username=admin" -d "scope=member-of-groups:readers"