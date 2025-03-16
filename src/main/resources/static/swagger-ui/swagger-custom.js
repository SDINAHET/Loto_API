// Fonction pour lire le token depuis un cookie
function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
  return null;
}

// Plugin pour gérer l'authentification avec cookie et Local Storage
const cookieAndLocalStorageAuthPlugin = {
  // Avant chaque requête, ajoute le token dans le header Authorization
  requestInterceptor: (request) => {
      let token = localStorage.getItem('jwtToken');
      if (!token) {
          token = getCookie('jwtToken'); // ✅ Utilisation de jwtToken
      }
      if (token) {
          request.headers['Authorization'] = `Bearer ${token}`;
      }
      return request;
  },

  // Après une requête de login, stocke le token dans le Local Storage
  responseInterceptor: (response) => {
      if (response.data && response.data.token) {
          localStorage.setItem('jwtToken', response.data.token); // ✅ Utilisation de jwtToken
      }
      return response;
  }
};

// Ajout du plugin à Swagger UI
window.onload = function() {
    const ui = SwaggerUIBundle({
        url: '/v3/api-docs',
        dom_id: '#swagger-ui',
        presets: [
            SwaggerUIBundle.presets.apis,
            SwaggerUIStandalonePreset
        ],
        plugins: [
            cookieAndLocalStorageAuthPlugin
        ],
        layout: "BaseLayout"
    });
    window.ui = ui;
};


// Ajout du requestInterceptor
axios.interceptors.request.use(cookieAndLocalStorageAuthPlugin.requestInterceptor);

// Ajout du responseInterceptor
axios.interceptors.response.use(cookieAndLocalStorageAuthPlugin.responseInterceptor);

