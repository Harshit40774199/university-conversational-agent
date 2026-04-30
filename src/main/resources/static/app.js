const modulesList = document.getElementById("modules-list");
const chatToggle = document.getElementById("chat-toggle");
const chatWidget = document.getElementById("chatbot-widget");
const chatClose = document.getElementById("chat-close");
const chatBox = document.getElementById("chat-box");
const chatInput = document.getElementById("chat-input");
const sendBtn = document.getElementById("send-btn");
const panelTiles = document.querySelectorAll(".panel-tile");
const detailPanelContent = document.getElementById("detail-panel-content");
const panelTitle = document.getElementById("panel-title");
const panelSubtitle = document.getElementById("panel-subtitle");
const bannerChatBtn = document.getElementById("banner-chat-btn");
const ghostButtons = document.querySelectorAll(".ghost-btn");

const panelTemplates = {
  overview: {
    title: "Welcome",
    subtitle: "This page stays inside your project instead of opening Napier URLs in a new tab.",
    content: `
      <div class="detail-hero">
        <h4>Home-style student dashboard</h4>
        <p>This version is designed to feel closer to a student portal home page while keeping everything inside your own system.</p>
      </div>
      <div class="detail-grid">
        <div class="info-card">
          <h5>What changed</h5>
          <ul>
            <li>Tiles now open internal panels instead of popping open URLs.</li>
            <li>Uni Assist is ready to answer from the backend when you ask a question.</li>
            <li>Your modules remain connected to the backend and database.</li>
          </ul>
        </div>
        <button class="detail-action" data-prompt="What can you help me with today?">Ask Uni Assist</button>
      </div>`
  },
  moodle: {
    title: "Moodle",
    subtitle: "A home-style summary panel instead of launching the Napier page.",
    content: `
      <div class="detail-hero">
        <h4>Moodle overview</h4>
        <p>Use this section as your internal Moodle-style entry point for modules, deadlines and study tasks.</p>
      </div>
      <div class="detail-grid">
        <div class="info-card"><h5>Common actions</h5><ul><li>Check module spaces</li><li>Review coursework deadlines</li><li>Open notes and study resources</li></ul></div>
        <div class="info-card"><h5>Connected source</h5><p>The backend can still use your Moodle source link behind the scenes, but users stay on this page.</p></div>
        <button class="detail-action" data-prompt="Show me my module information from Moodle">Ask Uni Assist about Moodle</button>
      </div>`
  },
  timetable: {
    title: "Timetable",
    subtitle: "A built-in timetable panel with chatbot help.",
    content: `
      <div class="detail-hero">
        <h4>Timetable panel</h4>
        <p>This is where your class schedule summary appears inside the project instead of opening another site.</p>
      </div>
      <div class="detail-grid">
        <div class="info-card"><h5>Week view</h5><p>Monday to Friday schedule blocks can be added here once you send the exact timetable details or link.</p></div>
        <div class="info-card"><h5>How to improve it</h5><p>Send the real timetable URL or timetable structure and I can make this look even closer to your Napier view.</p></div>
        <button class="detail-action" data-prompt="Show me my timetable information">Ask Uni Assist about my timetable</button>
      </div>`
  },
  fees: {
    title: "Fees and funding",
    subtitle: "Money support shown in an internal page style.",
    content: `
      <div class="detail-hero">
        <h4>Fees and funding</h4>
        <p>Keep money guidance inside your project while still letting the backend use the saved source URL in the background.</p>
      </div>
      <div class="detail-grid">
        <div class="info-card"><h5>Topics</h5><ul><li>Tuition payment help</li><li>Funding guidance</li><li>Money support questions</li></ul></div>
        <div class="info-card"><h5>Behaviour</h5><p>Clicking this tile no longer opens the Napier money page in a new tab.</p></div>
        <button class="detail-action" data-prompt="Tell me about fees and funding">Ask Uni Assist about fees</button>
      </div>`
  },
  email: {
    title: "Email",
    subtitle: "A portal-style email shortcut panel.",
    content: `
      <div class="detail-hero">
        <h4>Email overview</h4>
        <p>Show inbox shortcuts, important notices and support links here without leaving the home page.</p>
      </div>
      <div class="detail-grid">
        <div class="info-card"><h5>Use ideas</h5><ul><li>Unread count</li><li>Important messages</li><li>Quick sign-in button later</li></ul></div>
        <button class="detail-action" data-prompt="Help me with my student email">Ask Uni Assist about email</button>
      </div>`
  },
  records: {
    title: "Student records",
    subtitle: "A simple internal records overview.",
    content: `
      <div class="detail-hero">
        <h4>Student records</h4>
        <p>Use this panel for registration, profile status and record summaries.</p>
      </div>
      <div class="detail-grid">
        <div class="info-card"><h5>Suggested cards</h5><ul><li>Registration status</li><li>Programme details</li><li>Contact information</li></ul></div>
        <button class="detail-action" data-prompt="What student record information can I check here?">Ask Uni Assist</button>
      </div>`
  },
  library: {
    title: "Library",
    subtitle: "Library help without leaving the homepage.",
    content: `
      <div class="detail-hero">
        <h4>Library tools</h4>
        <p>Search support, reading lists and study guidance can be shown here as internal content blocks.</p>
      </div>
      <div class="detail-grid">
        <div class="info-card"><h5>Useful items</h5><ul><li>Reading lists</li><li>Borrowing help</li><li>Study support</li></ul></div>
        <button class="detail-action" data-prompt="Help me with library and reading lists">Ask Uni Assist</button>
      </div>`
  },
  support: {
    title: "Support",
    subtitle: "Quick help inside your portal.",
    content: `
      <div class="detail-hero">
        <h4>Support centre</h4>
        <p>Use Uni Assist for quick questions, then add more support panels later for IT, wellbeing and student advice.</p>
      </div>
      <div class="detail-grid">
        <div class="info-card"><h5>Support ideas</h5><ul><li>IT support</li><li>Wellbeing</li><li>Report an issue</li></ul></div>
        <button class="detail-action" data-prompt="I need help with student support">Ask Uni Assist</button>
      </div>`
  }
};

function renderPanel(panelKey) {
  const panel = panelTemplates[panelKey] || panelTemplates.overview;
  panelTitle.textContent = panel.title;
  panelSubtitle.textContent = panel.subtitle;
  detailPanelContent.innerHTML = panel.content;
  bindDetailActions();
}

function bindDetailActions() {
  document.querySelectorAll(".detail-action").forEach((button) => {
    button.addEventListener("click", async () => {
      openChat();
      chatInput.value = button.dataset.prompt || "Help me";
      await sendMessage();
    });
  });
}

function openChat() {
  chatWidget.classList.remove("hidden");
  chatInput.focus();
}

function closeChat() {
  chatWidget.classList.add("hidden");
}

function addMessage(text, sender, source = "") {
  const div = document.createElement("div");
  div.className = `message ${sender}`;

  const label = document.createElement("div");
  label.className = "message-label";
  label.textContent = sender === "user" ? "You" : "Uni Assist";
  div.appendChild(label);

  if (sender === "bot" && source) {
    const badge = document.createElement("span");
    badge.className = "message-source";
    badge.textContent = source;
    label.appendChild(badge);
  }

  const body = document.createElement("div");
  body.className = "message-body";
  body.textContent = text;
  div.appendChild(body);

  chatBox.appendChild(div);
  chatBox.scrollTop = chatBox.scrollHeight;
}

async function loadModules() {
  try {
    const response = await fetch("/api/modules");
    const modules = await response.json();
    modulesList.innerHTML = "";

    modules.forEach((module) => {
      const card = document.createElement("a");
      card.className = "module-card module-card-link";
      card.href = `/modules/${module.slug}`;

      card.innerHTML = `
        <h4>${module.name} (${module.code})</h4>
        <p><strong>Lecturer:</strong> ${module.lecturer ?? "N/A"}</p>
        <p><strong>Deadline:</strong> ${module.deadline ?? "N/A"}</p>
        <p><strong>Book:</strong> ${module.book ?? "N/A"}</p>
        <div class="module-card-actions">
          <span class="module-link-btn">Open module page</span>
          <button type="button" class="ask-module-btn" data-prompt="Tell me about ${module.code}">Ask Uni Assist</button>
        </div>
      `;

      modulesList.appendChild(card);
    });

    document.querySelectorAll(".ask-module-btn").forEach((button) => {
      button.addEventListener("click", async (event) => {
        event.preventDefault();
        event.stopPropagation();
        openChat();
        chatInput.value = button.dataset.prompt;
        await sendMessage();
      });
    });

  } catch (error) {
    modulesList.innerHTML = '<p class="status">Could not load modules. Make sure the backend and MySQL are running.</p>';
  }
}

async function sendMessage() {
  const message = chatInput.value.trim();
  if (!message) return;

  addMessage(message, "user");
  chatInput.value = "";

  try {
    const response = await fetch("/api/chat", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ sender: "student1", message })
    });

    const data = await response.json();
    addMessage(data.reply || "Sorry, I do not have an answer for that yet.", "bot", data.source || "");
  } catch (error) {
    addMessage("Could not connect to the backend. Start Spring Boot first.", "bot");
  }
}

panelTiles.forEach((tile) => {
  tile.addEventListener("click", () => {
    renderPanel(tile.dataset.panel);
  });
});

ghostButtons.forEach((button) => {
  button.addEventListener("click", () => {
    renderPanel(button.dataset.panel);
  });
});

bannerChatBtn.addEventListener("click", () => {
  openChat();
});

chatToggle.addEventListener("click", (event) => {
  event.stopPropagation();
  if (chatWidget.classList.contains("hidden")) {
    openChat();
  } else {
    closeChat();
  }
});

chatClose.addEventListener("click", closeChat);
sendBtn.addEventListener("click", sendMessage);

chatInput.addEventListener("keydown", (event) => {
  if (event.key === "Enter") {
    sendMessage();
  }
});

chatWidget.addEventListener("click", (event) => {
  event.stopPropagation();
});

document.addEventListener("click", (event) => {
  if (!chatWidget.classList.contains("hidden") && !chatWidget.contains(event.target) && event.target !== chatToggle) {
    closeChat();
  }
});

addMessage("Hello. I am Uni Assist. Ask me about anything.", "bot");
renderPanel("overview");
loadModules();