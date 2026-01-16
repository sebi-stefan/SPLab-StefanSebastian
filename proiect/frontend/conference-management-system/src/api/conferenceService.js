import api from "./axios";

export const getAllConferences = async () => {
  const response = await api.get("/conference");
  return response.data;
};

export const getAllConferencesByCurrentOrganizer = async () => {
  const response = await api.get("conference/currentUser");
  return response.data;
};

export const saveConference = async (conferenceData) => {
  const response = await api.post("/conference", conferenceData);
  return response.data;
};

export const editConference = async (conferenceId, conferenceData) => {
  const response = await api.post(
    `/conference/${conferenceId}`,
    conferenceData,
  );
  return response.data;
};

export const deleteConference = async (conferenceId) => {
  const response = await api.delete(`/conference/${conferenceId}`);
  return response.status;
};
