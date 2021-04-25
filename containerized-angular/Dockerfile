FROM node:14.16.1
WORKDIR /app
ADD . /app
RUN npm install
RUN npx ng build --output-path=dist
