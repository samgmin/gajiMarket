import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8888/board",
  headers: {
    "Content-Type": "application/json",
  }
});