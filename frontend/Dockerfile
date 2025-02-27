FROM node:18-alpine AS build-stage
WORKDIR /app
COPY package.json yarn.lock ./
RUN yarn install
COPY . .
RUN yarn build

FROM node:18-alpine AS run-stage
WORKDIR /app
COPY --from=build-stage /app .
CMD ["yarn", "dev", "--host"]
