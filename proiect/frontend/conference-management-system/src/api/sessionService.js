import api from "./axios";

export const getSessionsByConferenceId = async (conferenceId) => {
  const response = await api.get(`/session/conference/${conferenceId}`);
  return response.data;
};

export const addSession = async (conferenceId, sessionData) => {
  const response = await api.post(
    `/session/create/${conferenceId}`,
    sessionData,
  );
  return response.data;
};

export const editSession = async (sessionId, sessionData) => {
  const response = await api.post(`/session/${sessionId}`, sessionData);
  return response.data;
};

export const deleteSession = async (sessionId) => {
  const response = await api.delete(`/session/${sessionId}`);
  return response.data;
};
