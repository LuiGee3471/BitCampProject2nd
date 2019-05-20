const weather = document.querySelector(".weather");
const weatherinfo = document.querySelector(".weatherinfo");
const API_KEY = "76b90e82bc42d40347901d67ad32f0a2";
const COORDS = "coords";

function getWeather(lat, lng) {
  fetch(
    `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&appid=${API_KEY}&units=metric`
  )
    .then(function(response) {
      return response.json();
    })
    .then(function(json) {
      const mainWeather = json.weather[0].main;
      const temp = Math.round(json.main.temp);
      const place = json.name;
      const info = `${temp}°`;
      weatherinfo.append(info);
      if (mainWeather === "Rain") {
        const str = '<i class="fas fa-umbrella fa-3x" style="color: #1762c6;"></i>';
        $(".weatherpic").append(str);
      } else if (mainWeather === "Clouds") {
        const str = '<i class="fas fa-cloud fa-3x" style="color: #5d5c5b;"></i>';
        $(".weatherpic").append(str);
      } else if (mainWeather === "Clear") {
        const str = '<i class="fas fa-sun fa-3x" style="color: #fdb60a;"></i>';
        $(".weatherpic").append(str);
      } else if (mainWeather === "Haze") {
        const str = '<i class="fas fa-smog fa-3x" style="color: #1762c6;"></i>';
        $(".weatherpic").append(str);
      } else if (mainWeather === "Mist") {
        const str = "<img src='images/weather/mist.png' widith='50px' height='50px'>";
        $(".weatherpic").append(str);
      }
    });
}

function saveCoords(coordsObj) {
  localStorage.setItem(COORDS, JSON.stringify(coordsObj));
}

function handleGeoSucces(position) {
  const latitude = position.coords.latitude;
  const longitude = position.coords.longitude;
  const coordsObj = {
    latitude,
    longitude,
  };
  saveCoords(coordsObj);
  getWeather(latitude, longitude);
}

function handleGeoError() {
  console.log("Cant access geo location");
}

function askForCoords() {
  navigator.geolocation.getCurrentPosition(handleGeoSucces, handleGeoError);
}

function loadCoords() {
  const loadedCoords = localStorage.getItem(COORDS);
  if (loadedCoords === null) {
    askForCoords();
  } else {
    const parseCoords = JSON.parse(loadedCoords);
    getWeather(parseCoords.latitude, parseCoords.longitude);
  }
}

function init() {
  loadCoords();
}

init();
