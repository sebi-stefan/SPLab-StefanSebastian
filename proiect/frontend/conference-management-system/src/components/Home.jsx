import NavBar from "./NavBar/NavBar";
import { useState, useEffect } from "react";
import Discover from "./Discover";
import MyConferences from "./MyConferences";

export const myConferences = [
  {
    title: "Title 1",
    organizer: {
      id: 0,
      email: "string",
      firstName: "John",
      lastName: "Mich",
      bio: "string",
      organization: "string",
      profilePictureUrl: "string",
    },
    startTime: "2025-03-13",
    endTime: "2025-09-02",
    venueAddress: "80458 Northport Terrace",
    venueName: "Jones and Sons",
    maxAttendees: 85,
    registrationDeadline: "2025-04-01",
    websiteUrl:
      "http://statcounter.com/nunc/nisl/duis.png?sapien=duis&sapien=mattis&non=egestas&mi=metus&integer=aenean&ac=fermentum&neque=donec&duis=ut&bibendum=mauris&morbi=eget&non=massa&quam=tempor&nec=convallis&dui=nulla&luctus=neque&rutrum=libero&nulla=convallis&tellus=eget&in=eleifend&sagittis=luctus&dui=ultricies&vel=eu&nisl=nibh&duis=quisque&ac=id&nibh=justo&fusce=sit&lacus=amet&purus=sapien&aliquet=dignissim&at=vestibulum&feugiat=vestibulum&non=ante&pretium=ipsum&quis=primis&lectus=in&suspendisse=faucibus&potenti=orci&in=luctus&eleifend=et&quam=ultrices&a=posuere&odio=cubilia&in=curae&hac=nulla&habitasse=dapibus&platea=dolor&dictumst=vel&maecenas=est&ut=donec&massa=odio&quis=justo&augue=sollicitudin&luctus=ut&tincidunt=suscipit&nulla=a&mollis=feugiat&molestie=et&lorem=eros&quisque=vestibulum&ut=ac&erat=est&curabitur=lacinia&gravida=nisi&nisi=venenatis&at=tristique&nibh=fusce&in=congue&hac=diam&habitasse=id&platea=ornare&dictumst=imperdiet&aliquam=sapien&augue=urna&quam=pretium&sollicitudin=nisl&vitae=ut&consectetuer=volutpat&eget=sapien&rutrum=arcu",
    status: "PUBLISHED",
    description:
      "A premier gathering of scientists, researchers, and innovators presenting groundbreaking discoveries across physics, biology, chemistry, and environmental sciences.",
    tags: "AI, MACHINE, LEARNING",
    coverImageUrl: "",
  },
  {
    title: "Title 2",
    organizer: {
      id: 0,
      email: "string",
      firstName: "John",
      lastName: "Mich",
      bio: "string",
      organization: "string",
      profilePictureUrl: "string",
    },
    startTime: "2024-12-27",
    endTime: "2025-09-11",
    venueAddress: "785 Nelson Trail",
    venueName: "Bins, McClure and Kunde",
    maxAttendees: 95,
    registrationDeadline: "2025-03-06",
    websiteUrl:
      "https://accuweather.com/convallis/duis/consequat/dui/nec/nisi.png?faucibus=felis&orci=fusce&luctus=posuere&et=felis&ultrices=sed&posuere=lacus&cubilia=morbi&curae=sem&duis=mauris&faucibus=laoreet&accumsan=ut&odio=rhoncus&curabitur=aliquet&convallis=pulvinar&duis=sed&consequat=nisl&dui=nunc&nec=rhoncus&nisi=dui&volutpat=vel&eleifend=sem&donec=sed&ut=sagittis&dolor=nam&morbi=congue&vel=risus&lectus=semper&in=porta&quam=volutpat&fringilla=quam&rhoncus=pede&mauris=lobortis&enim=ligula&leo=sit&rhoncus=amet&sed=eleifend&vestibulum=pede&sit=libero&amet=quis&cursus=orci&id=nullam&turpis=molestie&integer=nibh&aliquet=in&massa=lectus&id=pellentesque&lobortis=at&convallis=nulla&tortor=suspendisse&risus=potenti&dapibus=cras&augue=in&vel=purus&accumsan=eu&tellus=magna&nisi=vulputate&eu=luctus&orci=cum&mauris=sociis&lacinia=natoque&sapien=penatibus&quis=et&libero=magnis&nullam=dis&sit=parturient&amet=montes&turpis=nascetur&elementum=ridiculus&ligula=mus&vehicula=vivamus&consequat=vestibulum&morbi=sagittis&a=sapien&ipsum=cum&integer=sociis&a=natoque&nibh=penatibus&in=et&quis=magnis&justo=dis&maecenas=parturient&rhoncus=montes&aliquam=nascetur&lacus=ridiculus&morbi=mus&quis=etiam&tortor=vel&id=augue&nulla=vestibulum&ultrices=rutrum&aliquet=rutrum&maecenas=neque&leo=aenean&odio=auctor&condimentum=gravida&id=sem&luctus=praesent",
    status: "PUBLISHED",
    description:
      "A premier gathering of scientists, researchers, and innovators presenting groundbreaking discoveries across physics, biology, chemistry, and environmental sciences.",
    tags: "AI, COMPUTER SCIENCE, FINANCE",
    coverImageUrl: "",
  },
  {
    title: "Scientific discovery of the century",
    organizer: {
      id: 0,
      email: "string",
      firstName: "John",
      lastName: "Mich",
      bio: "string",
      organization: "string",
      profilePictureUrl: "string",
    },
    startTime: "2025-07-23",
    endTime: "2025-09-18",
    venueAddress: "71 Sachtjen Alley",
    venueName: "Veum-Kiehn",
    maxAttendees: 58,
    registrationDeadline: "2025-04-04",
    websiteUrl:
      "https://craigslist.org/a/feugiat/et/eros/vestibulum/ac/est.json?est=in&lacinia=congue&nisi=etiam&venenatis=justo&tristique=etiam&fusce=pretium&congue=iaculis&diam=justo&id=in&ornare=hac&imperdiet=habitasse&sapien=platea&urna=dictumst&pretium=etiam&nisl=faucibus&ut=cursus&volutpat=urna&sapien=ut&arcu=tellus&sed=nulla&augue=ut&aliquam=erat&erat=id&volutpat=mauris&in=vulputate&congue=elementum&etiam=nullam&justo=varius&etiam=nulla&pretium=facilisi&iaculis=cras&justo=non&in=velit&hac=nec&habitasse=nisi&platea=vulputate&dictumst=nonummy&etiam=maecenas&faucibus=tincidunt&cursus=lacus&urna=at&ut=velit&tellus=vivamus&nulla=vel&ut=nulla&erat=eget&id=eros",
    status: "PUBLISHED",
    description:
      "A premier gathering of scientists, researchers, and innovators presenting groundbreaking discoveries across physics, biology, chemistry, and environmental sciences. A premier gathering of scientists, researchers, and innovators presenting groundbreaking discoveries across physics, biology, chemistry, and environmental sciences.",
    tags: "BUSINESS, SALES",
    coverImageUrl: "",
  },
  {
    title: "Title 4",
    organizer: {
      id: 0,
      email: "string",
      firstName: "John",
      lastName: "Mich",
      bio: "string",
      organization: "string",
      profilePictureUrl: "string",
    },
    startTime: "2025-06-04",
    endTime: "2025-09-10",
    venueAddress: "53433 Eastwood Court",
    venueName: "Grimes, Jakubowski and West",
    maxAttendees: 59,
    registrationDeadline: "2025-08-03",
    websiteUrl:
      "https://marketwatch.com/ut/mauris/eget.js?congue=in&etiam=sapien&justo=iaculis&etiam=congue&pretium=vivamus&iaculis=metus&justo=arcu&in=adipiscing&hac=molestie&habitasse=hendrerit&platea=at&dictumst=vulputate&etiam=vitae&faucibus=nisl&cursus=aenean&urna=lectus&ut=pellentesque&tellus=eget&nulla=nunc&ut=donec&erat=quis&id=orci&mauris=eget&vulputate=orci&elementum=vehicula&nullam=condimentum&varius=curabitur&nulla=in&facilisi=libero&cras=ut&non=massa&velit=volutpat&nec=convallis&nisi=morbi&vulputate=odio&nonummy=odio&maecenas=elementum&tincidunt=eu&lacus=interdum&at=eu&velit=tincidunt&vivamus=in&vel=leo&nulla=maecenas&eget=pulvinar&eros=lobortis&elementum=est&pellentesque=phasellus&quisque=sit&porta=amet&volutpat=erat&erat=nulla&quisque=tempus",
    status: "PUBLISHED",
    description:
      "A premier gathering of scientists, researchers, and innovators presenting groundbreaking discoveries across physics, biology, chemistry, and environmental sciences.",
    tags: "RESEARCH, SCIENCE, INNOVATION",
    coverImageUrl: "",
  },
  {
    title: "Title 5",
    organizer: {
      id: 0,
      email: "string",
      firstName: "John",
      lastName: "Mich",
      bio: "string",
      organization: "string",
      profilePictureUrl: "string",
    },
    startTime: "2025-11-26",
    endTime: "2025-11-30",
    venueAddress: "925 Muir Point",
    venueName: "Wyman-Nader",
    maxAttendees: 82,
    registrationDeadline: "2025-05-19",
    websiteUrl:
      "http://theguardian.com/morbi/non/quam/nec/dui/luctus.jsp?varius=pretium&ut=iaculis&blandit=justo&non=in&interdum=hac&in=habitasse&ante=platea&vestibulum=dictumst&ante=etiam&ipsum=faucibus&primis=cursus&in=urna&faucibus=ut&orci=tellus&luctus=nulla&et=ut&ultrices=erat&posuere=id&cubilia=mauris&curae=vulputate&duis=elementum&faucibus=nullam&accumsan=varius&odio=nulla&curabitur=facilisi&convallis=cras&duis=non&consequat=velit&dui=nec&nec=nisi&nisi=vulputate&volutpat=nonummy&eleifend=maecenas&donec=tincidunt&ut=lacus&dolor=at&morbi=velit&vel=vivamus&lectus=vel&in=nulla&quam=eget&fringilla=eros&rhoncus=elementum&mauris=pellentesque&enim=quisque&leo=porta&rhoncus=volutpat&sed=erat",
    status: "PUBLISHED",
    description:
      "A premier gathering of scientists, researchers, and innovators presenting groundbreaking discoveries across physics, biology, chemistry, and environmental sciences.",
    tags: "STARTUP, TECH",
    coverImageUrl: "",
  },
  {
    title: "Title 6",
    organizer: {
      id: 0,
      email: "string",
      firstName: "John",
      lastName: "Mich",
      bio: "string",
      organization: "string",
      profilePictureUrl: "string",
    },
    startTime: "2024-12-15",
    endTime: "2025-07-16",
    venueAddress: "7 Hoepker Road",
    venueName: "Schmitt and Sons",
    maxAttendees: 50,
    registrationDeadline: "2025-07-20",
    websiteUrl:
      "http://blog.com/bibendum.xml?elementum=erat&eu=curabitur&interdum=gravida&eu=nisi&tincidunt=at&in=nibh&leo=in&maecenas=hac&pulvinar=habitasse&lobortis=platea&est=dictumst&phasellus=aliquam&sit=augue&amet=quam&erat=sollicitudin&nulla=vitae&tempus=consectetuer&vivamus=eget&in=rutrum&felis=at&eu=lorem&sapien=integer&cursus=tincidunt&vestibulum=ante&proin=vel&eu=ipsum&mi=praesent&nulla=blandit&ac=lacinia",
    status: "PUBLISHED",
    description:
      "A premier gathering of scientists, researchers, and innovators presenting groundbreaking discoveries across physics, biology, chemistry, and environmental sciences.",
    tags: "HEALTHCARE, MEDICINE",
    coverImageUrl: "",
  },
];

function Home() {
  const [activeTab, setActiveTab] = useState("discover");
  const [selectedConference, setSelectedConference] = useState(null);
  const [isConferenceCreateModalOpen, setIsConferenceCreateModalOpen] =
    useState(false);
  const [currentUserRole, setCurrentUserRole] = useState("organizer");
  const [isViewModalOpen, setIsViewModalOpen] = useState(false);

  // const userRoles = ["attendee", "organizer", "admin"];

  return (
    <div
      className={`index-page ${isConferenceCreateModalOpen || isViewModalOpen ? "hidden" : ""}`}
    >
      <NavBar
        activeTab={activeTab}
        onTabChange={setActiveTab}
        isConferenceCreateModalOpen={isConferenceCreateModalOpen}
        setIsConferenceCreateModalOpen={setIsConferenceCreateModalOpen}
        isViewModalOpen={isViewModalOpen}
      />
      {activeTab === "discover" && (
        <Discover
          setSelectedConference={setSelectedConference}
          selectedConference={selectedConference}
          setIsViewModalOpen={setIsViewModalOpen}
          isViewModalOpen={isViewModalOpen}
        />
      )}
      {activeTab === "my-conferences" && (
        <MyConferences
          setSelectedConference={setSelectedConference}
          selectedConference={selectedConference}
          isConferenceCreateModalOpen={isConferenceCreateModalOpen}
          setIsConferenceCreateModalOpen={setIsConferenceCreateModalOpen}
          currentUserRole={currentUserRole}
          setCurrentUserRole={setCurrentUserRole}
          isViewModalOpen={isViewModalOpen}
          setIsViewModalOpen={setIsViewModalOpen}
        />
      )}
    </div>
  );
}

export default Home;
