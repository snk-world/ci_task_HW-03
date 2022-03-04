#!/bin/bash

echo "git push..."
git add .
git commit -m " another commit %1"
git push origin main
