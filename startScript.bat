cd accountmaker-backend

docker run -d --name accountmaker-backend -p 8080:8080 accountmaker-backend

cd ../

echo "Backend is started"

cd accountmaker-frontend
docker run -d --name accountmaker-frontend -p 3000:3000 accountmaker-frontend

echo "Frontend is started"