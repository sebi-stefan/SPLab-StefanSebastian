import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
});

// add auth header to request before any call
api.interceptors.request.use(
  (config) => {
    const email = localStorage.getItem("email");
    const password = localStorage.getItem("password");

    if (email && password) {
      const credentials = btoa(`${email}:${password}`);
      config.headers.Authorization = `Basic ${credentials}`;
    }

    return config;
  },
  (error) => {
    localStorage.removeItem("email");
    localStorage.removeItem("password");
    return Promise.reject(error);
  },
);

// handle unauthorized responses
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem("email");
      localStorage.removeItem("password");
      console.log(error);

      if (window.location.pathname !== "/login") {
        window.location.href = "/login";
      }
    }
  },
);

export default api;
