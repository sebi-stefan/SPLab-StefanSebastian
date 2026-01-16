import "./MyConferences.css";
import ConferenceCard from "./ConferenceCard";
import { useState, useEffect } from "react";
import Conference from "./CreateConferece/Conference";
import ViewConference from "./ViewConference";
import CreateSession from "./CreateConferece/CreateSession";
import { getAllConferencesByCurrentOrganizer } from "../api/conferenceService";
import { addSession } from "../api/sessionService";
import { getSessionsByConferenceId } from "../api/sessionService";
import { editSession } from "../api/sessionService";
import { deleteSession } from "../api/sessionService";
import { deleteConference } from "../api/conferenceService";
import CreateConference from "./CreateConferece/CreateConference";

function MyConferences({
  selectedConference,
  setSelectedConference,
  isConferenceCreateModalOpen,
  setIsConferenceCreateModalOpen,
  currentUserRole,
  isViewModalOpen,
  setIsViewModalOpen,
}) {
  // useEffect(() => {
  //   console.log("selectedConference changed:", selectedConference);
  // }, [selectedConference]);

  const [conferences, setConferences] = useState([]);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [isSessionModalOpen, setIsSessionModalOpen] = useState(false);
  const [editingSession, setEditingSession] = useState(null);
  const [editingSessionIndex, setEditingSessionIndex] = useState(null);

  const fetchConferences = async () => {
    try {
      const conferences = await getAllConferencesByCurrentOrganizer();

      const conferencesWithSessions = await Promise.all(
        conferences.map(async (conf) => ({
          ...conf,
          sessions: (await getSessionsByConferenceId(conf.id)) || [],
        })),
      );

      setConferences(conferencesWithSessions);

      // Update selectedConference if it exists
      if (selectedConference) {
        const updatedSelected = conferencesWithSessions.find(
          (conf) => conf.id === selectedConference.id,
        );
        if (updatedSelected) {
          setSelectedConference(updatedSelected);
        }
      }

      console.log(conferences);
    } catch (error) {
      console.error("Failed to fetch conferences:", error);
      setConferences([]);
    }
  };

  useEffect(() => {
    fetchConferences();
  }, []);

  const openConferenceCreateModal = () => {
    setIsConferenceCreateModalOpen(true);
  };

  const closeAllModals = () => {
    setIsConferenceCreateModalOpen(false);
    setIsViewModalOpen(false);
    setIsEditModalOpen(false);
    setIsSessionModalOpen(false);
    setSelectedConference(null);
    setEditingSession(null);
    setEditingSessionIndex(null);
  };

  const handleEditConference = () => {
    setIsViewModalOpen(false);
    setIsConferenceCreateModalOpen(true);
  };

  const handleSaveConference = (updatedConference) => {
    setConferences((prevConferences) =>
      prevConferences.map((conf) =>
        conf === selectedConference ? updatedConference : conf,
      ),
    );
    setSelectedConference(updatedConference);
    setIsEditModalOpen(false);
    setIsViewModalOpen(true);
  };

  const handleDeleteConference = async (conferenceToDelete) => {
    try {
      await deleteConference(conferenceToDelete.id);
    } catch (err) {
      console.error(err);
    }

    setConferences((prevConferences) =>
      prevConferences.filter((conf) => conf !== conferenceToDelete),
    );
    closeAllModals();
  };

  const handleAddSession = async () => {
    setEditingSession(null);
    setEditingSessionIndex(null);
    setIsSessionModalOpen(true);
  };

  const handleEditSession = (session) => {
    const sessionIndex = selectedConference.sessions.findIndex(
      (s) => s === session,
    );
    setEditingSession(session);
    setEditingSessionIndex(sessionIndex);
    setIsSessionModalOpen(true);
  };

  const handleSaveSession = async (sessionData) => {
    const updatedConference = { ...selectedConference };

    if (editingSessionIndex !== null) {
      updatedConference.sessions[editingSessionIndex] = sessionData;

      try {
        await editSession(editingSession.id, sessionData);
      } catch (err) {
        console.error(err);
      }
    } else {
      updatedConference.sessions = [
        ...(updatedConference.sessions || []),
        { ...sessionData, id: Date.now() },
      ];

      try {
        const add = async (conferenceId, sessionData) => {
          const response = addSession(selectedConference.id, sessionData);
          return response.data;
        };

        console.log("selected conference:", selectedConference);
        console.log("session data:", sessionData);
        await add(selectedConference.id, sessionData);
      } catch (err) {
        console.error(err);
      }
    }

    setConferences((prevConferences) =>
      prevConferences.map((conf) =>
        conf === selectedConference ? updatedConference : conf,
      ),
    );
    setSelectedConference(updatedConference);
    setIsSessionModalOpen(false);
    setEditingSession(null);
    setEditingSessionIndex(null);
  };

  const handleDeleteSession = async (sessionToDelete) => {
    try {
      const del = async (sessionToDelete) => {
        const response = deleteSession(sessionToDelete.id);
        return response.data;
      };

      await del(sessionToDelete);
    } catch (err) {
      console.error(err);
    }

    const updatedConference = {
      ...selectedConference,
      sessions: selectedConference.sessions.filter(
        (s) => s !== sessionToDelete,
      ),
    };

    setConferences((prevConferences) =>
      prevConferences.map((conf) =>
        conf === selectedConference ? updatedConference : conf,
      ),
    );
    setSelectedConference(updatedConference);
  };

  return (
    <section className="my-conferences">
      <div
        className={`my-conferences-content ${
          isConferenceCreateModalOpen ||
          isViewModalOpen ||
          isEditModalOpen ||
          isSessionModalOpen
            ? "blurred"
            : ""
        }`}
      >
        <div className="my-conferences-header">
          <h2 className="my-conferences-title">Manage your conferences</h2>
          <p className="my-conferences-subtitle">
            Create new conferences or add sessions to existing conferences
          </p>
        </div>

        <div className="my-conferences-header-options">
          <p className="my-conferences-subtitle">Your current conferences:</p>
          <div className="my-conferences-options">
            <button
              className="my-conferences-btn"
              onClick={openConferenceCreateModal}
            >
              Create Conference
            </button>
          </div>
        </div>

        <div className="my-conferences-display-grid">
          {conferences.map((conference, index) => (
            <ConferenceCard
              key={index}
              conference={conference}
              mode="edit"
              setIsViewModalOpen={setIsViewModalOpen}
              currentUserRole={currentUserRole}
              setSelectedConference={setSelectedConference}
            />
          ))}
        </div>
      </div>

      {/* Create Conference Modal */}
      {isConferenceCreateModalOpen && (
        <div className="conference-modal-overlay" onClick={closeAllModals}>
          <div
            className="conference-modal-content"
            onClick={(e) => e.stopPropagation()}
          >
            <button className="conference-modal-close" onClick={closeAllModals}>
              ×
            </button>
            <Conference
              closeConferenceModal={closeAllModals}
              setConferences={setConferences}
              fetchConferences={fetchConferences}
            />
          </div>
        </div>
      )}

      {/* View Conference Modal */}
      {isViewModalOpen && selectedConference && (
        <div className="conference-modal-overlay" onClick={closeAllModals}>
          <div
            className="conference-modal-content"
            onClick={(e) => e.stopPropagation()}
          >
            <button className="conference-modal-close" onClick={closeAllModals}>
              ×
            </button>
            <ViewConference
              currentUserRole={currentUserRole}
              selectedConference={selectedConference}
              onEditConference={handleEditConference}
              onDeleteConference={handleDeleteConference}
              onAddSession={handleAddSession}
              onEditSession={handleEditSession}
              onDeleteSession={handleDeleteSession}
              fetchConferences={fetchConferences}
              conferences={conferences}
            />
          </div>
        </div>
      )}

      {/* Edit Conference Modal */}
      {isConferenceCreateModalOpen && selectedConference && (
        <div className="conference-modal-overlay" onClick={closeAllModals}>
          <div
            className="conference-modal-content"
            onClick={(e) => e.stopPropagation()}
          >
            <button className="conference-modal-close" onClick={closeAllModals}>
              ×
            </button>
            <Conference
              conferenceData={selectedConference}
              onSave={handleSaveConference}
              isConferenceCreateModalOpen={isConferenceCreateModalOpen}
              setIsEditModalOpen={setIsEditModalOpen}
              setIsViewModalOpen={setIsViewModalOpen}
              setIsConferenceModalOpen={setIsConferenceCreateModalOpen}
              selectedConference={selectedConference}
              fetchConferences={fetchConferences}
            />
          </div>
        </div>
      )}

      {/* Session Modal (Add/Edit) */}
      {isSessionModalOpen && (
        <div className="conference-modal-overlay" onClick={closeAllModals}>
          <div
            className="conference-modal-content"
            onClick={(e) => e.stopPropagation()}
          >
            <button
              className="conference-modal-close"
              onClick={() => {
                setIsSessionModalOpen(false);
                setEditingSession(null);
                setEditingSessionIndex(null);
              }}
            >
              ×
            </button>
            <CreateSession
              isSessionOpen={true}
              setIsSessionOpen={setIsSessionModalOpen}
              fetchConferences={fetchConferences}
              sessions={selectedConference?.sessions || []}
              data={
                editingSession || {
                  title: "",
                  startTime: "",
                  endTime: "",
                  maxAttendees: "",
                  room: "",
                  description: "",
                }
              }
              editingIndex={editingSessionIndex}
              onSave={handleSaveSession}
              onCancel={() => {
                setIsSessionModalOpen(false);
                setEditingSession(null);
                setEditingSessionIndex(null);
              }}
            />
          </div>
        </div>
      )}
    </section>
  );
}

export default MyConferences;
