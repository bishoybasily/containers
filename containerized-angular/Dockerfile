FROM node:14.16.1 as builder
WORKDIR /app
ADD . /app
RUN npm install
RUN npx ng build --output-path=dist

FROM httpd:2.4
COPY --from=builder /app/dist/ /usr/local/apache2/htdocs/
