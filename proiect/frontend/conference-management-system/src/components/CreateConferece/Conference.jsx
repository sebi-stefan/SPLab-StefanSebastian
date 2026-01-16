import CreateConference from "./CreateConference";
import CreateSession from "./CreateSession";
import { useState } from "react";
import "./Conference.css";

function Conference({
  conferenceData,
  closeConferenceModal,
  setConferences,
  fetchConferences,
  isConferenceCreateModalOpen,
  setIsEditModalOpen,
  setIsViewModalOpen,
  setIsConferenceModalOpen,
  editConference,
  selectedConference,
}) {
  console.log("Conference component rendered, conferenceData:", conferenceData);

  const [isSessionOpen, setIsSessionOpen] = useState(false);
  const [sessions, setSessions] = useState([]);
  const [editingIndex, setEditingIndex] = useState(null);

  const [openSessionData, setOpenSessionData] = useState({
    title: "",
    startTime: "",
    endTime: "",
    maxAttendees: "",
    room: "",
    description: "",
  });

  return (
    <div className="conference-container">
      <CreateConference
        isSessionOpen={isSessionOpen}
        setIsSessionOpen={setIsSessionOpen}
        sessions={sessions}
        setSessions={setSessions}
        setOpenSessionData={setOpenSessionData}
        setEditingIndex={setEditingIndex}
        conferenceData={conferenceData}
        closeConferenceModal={closeConferenceModal}
        setConferences={setConferences}
        fetchConferences={fetchConferences}
        isConferenceCreateModalOpen={isConferenceCreateModalOpen}
        setIsEditModalOpen={setIsEditModalOpen}
        setIsViewModalOpen={setIsViewModalOpen}
        setIsConferenceModalOpen={setIsConferenceModalOpen}
        editConference={editConference}
        selectedConference={selectedConference}
      />
      <CreateSession
        isSessionOpen={isSessionOpen}
        setIsSessionOpen={setIsSessionOpen}
        sessions={sessions}
        setSessions={setSessions}
        data={openSessionData}
        editingIndex={editingIndex}
        setEditingIndex={setEditingIndex}
        fetchConferences={fetchConferences}
      />
    </div>
  );
}

export default Conference;
