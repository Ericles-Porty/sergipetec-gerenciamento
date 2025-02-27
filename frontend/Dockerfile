FROM node:20-alpine AS build-stage
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

FROM node:20-alpine AS run-stage
WORKDIR /app
COPY --from=build-stage /app . 

CMD ["npm", "run", "dev", "--", "--host"]
