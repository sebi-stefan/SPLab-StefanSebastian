import { useState, useMemo, useEffect } from "react";
import { Search } from "lucide-react";
import ConferenceCard from "./ConferenceCard";
import "./Discover.css";
import ViewConference from "./ViewConference";
import {
  getAllConferences,
  getAllConferencesByCurrentOrganizer,
} from "../api/conferenceService";
import { getSessionsByConferenceId } from "../api/sessionService";

const Discover = ({
  setSelectedConference,
  selectedConference,
  setIsViewModalOpen,
  isViewModalOpen,
}) => {
  const [conferences, setConferences] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");

  const fetchConferences = async () => {
    try {
      const conferences = await getAllConferences();

      const conferencesWithSessions = await Promise.all(
        conferences.map(async (conf) => ({
          ...conf,
          sessions: (await getSessionsByConferenceId(conf.id)) || [],
        })),
      );

      setConferences(conferencesWithSessions);

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

  const closeConferenceModal = () => {
    setIsViewModalOpen(false);
    setSelectedConference(null);
  };

  const filteredConferences = useMemo(() => {
    return conferences.filter((conf) => {
      const matchesSearch =
        searchQuery === "" ||
        conf.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
        conf.description.toLowerCase().includes(searchQuery.toLowerCase()) ||
        conf.venueName.toLowerCase().includes(searchQuery.toLowerCase());

      return matchesSearch;
    });
  }, [conferences, searchQuery]);

  return (
    <section className="discover">
      <div className="discover__header">
        <h2 className="discover__title">Discover Conferences</h2>
        <p className="discover__subtitle">
          Find and register for upcoming conferences that match your interests
        </p>
      </div>

      <div className="discover__search">
        <Search className="discover__search-icon" />
        <input
          type="text"
          placeholder="Search conferences by name, description, or location..."
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          className="discover__search-input"
        />
      </div>

      <div className="discover__filters">
        <p className="discover__filters-label">Filter by topics:</p>
      </div>

      <p className="discover__results-count">
        Showing {filteredConferences.length} of {conferences.length} conferences
      </p>

      {filteredConferences.length > 0 ? (
        <div className="discover__grid">
          {filteredConferences.map((conference, index) => (
            <ConferenceCard
              key={index}
              conference={conference}
              setSelectedConference={setSelectedConference}
              setIsViewModalOpen={setIsViewModalOpen}
            />
          ))}
        </div>
      ) : (
        <div className="discover__empty">
          <p className="discover__empty-title">No conferences found</p>
          <p className="discover__empty-text">
            Try adjusting your search or filter criteria
          </p>
        </div>
      )}

      {isViewModalOpen && selectedConference && (
        <div
          className="conference-modal-overlay"
          onClick={closeConferenceModal}
        >
          <div
            className="conference-modal-content"
            onClick={(e) => e.stopPropagation()}
          >
            <button
              className="conference-modal-close"
              onClick={closeConferenceModal}
            >
              ×
            </button>
            <ViewConference
              currentUserRole="attendee"
              selectedConference={selectedConference}
            />
          </div>
        </div>
      )}
    </section>
  );
};

export default Discover;
