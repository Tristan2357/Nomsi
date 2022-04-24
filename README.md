# Nomsi

[![CI](https://github.com/Tristan2357/Nomsi/actions/workflows/github-action.yml/badge.svg)](https://github.com/Tristan2357/Nomsi/actions/workflows/github-action.yml)
[![CodeFactor](https://www.codefactor.io/repository/github/tristan2357/nomsi/badge/main)](https://www.codefactor.io/repository/github/tristan2357/nomsi/overview/main)
[![code style: prettier](https://img.shields.io/badge/code_style-prettier-ff69b4.svg?style=flat-square)](https://github.com/prettier/prettier)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

### Description

This Readme is WIP and will be updated in the future, when you can actually find a recipe manager
here.

### Getting Started

Directly after a clone you have to initiate one first build:

- In root directory run `gradle bundle` or `./gradlew bundle`.

### Development

You will need 2 terminals to achieve hot reloading while coding.

- In server dir run: `gradle -t run` or in root dir run: `./gradlew -t :server:run`
- in client dir run: `yarn serve` or in root dir run: `yarn --cwd client serve`

### Production

- In root directory run `gradle bundle` or `./gradlew bundle`.

This will create a fat-jar file including everything. In production the built Vue project is served
by Ktor itself.

To execute the jar file run `java -jar build/server-0.0.1.jar`. This will try running on Port 80 (as
it's production),
if you want to test locally run `java -jar build/server-0.0.1.jar -port=SOME_PORT`. 
