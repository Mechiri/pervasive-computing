# pervasive-computing

README
Rote is a relationship application developed using Android Studio and Firebase as the database
backend. Rote is aimed to help users reflect on their current relationship(s) and their own needs
within a relationship.

APP FUNCTIONALITY
When the Rote Application starts, initial onboarding screens are displayed providing details
about the app’s purpose. From this, the user is asked to either create an account or log in to an
existing account. If a user creates a new account, an email, password, and preferred name are
collected leading to an initial survey required for all new users. Background information about
the user’s relationship preferences is collected in addition to a Love Language survey, with 15
questions, to understand the user’s Love Language preferences.

After finishing the initial survey, the app will prompt the user to continue to a landing
page/dashboard which contains a list of all partner profiles. Initially, if the user is new, there will
be no partner profiles. However, this landing page/dashboard also enables a user to add as
many partner profiles as they see fit. Each partner profile on the landing page/dashboard is
clickable, navigating a user to a specific partner page with two tabs to display either event for a
given partner or partner profile. On the events tab, a user can see all events associated with the
selected partner and either click the three dots to see a given event’s full details or click a plus
button on the bottom to add a new event. If a user adds a new event, they will be prompted
through a series of forms and upon successfully adding an event, brought back to the landing
page/dashboard.

On the partner’s profile page, a user can understand an average experience indicator and overall
love language experience. Additionally, the landing page/dashboard and partner page have a
user profile button allowing a user to access their personal profile. In a user’s personal profile, a
user can see their relationship expectations and love languages, which is gathered in the initial
survey, along with the ability to logout of the app. Logging out of the app will bring a user
directly to the login page.


Activity LifeCycle of ​ Rote
start - Starting Page
|
ActiveSignUp - Sign Up Page - Email
|
ActivePassword - Sign Up Page - Password
|
ActiveUserName - Sign Up Page - Username
|

WelcomePage - User Welcome Page
|
initialSurvey - Survey Questions will Start
{ Survey Pages 1 -5 - Fragments}
{ Survey Questions 1 - 15 - Fragments}
|
SurveyResult - Display Survey Results
|
LandingPage - where user can add partner profiles.
|

(same activities as shown the below picture)
start - Starting Page
|
ActiveLogin - Login Page
|
LandingPage - where user can add partner profiles.
| | |
-> partnerPage userProfile addPartnerForm
{partnerRecyclerViewAdapter}
|
{TAB LAYOUT- Page Adapter}-------------------------------------------
| |
eventTab------------------ partnerProfileTab
| |
EventForm EventDetail
{eventTab} {partnerEventRecyclerViewAdapter}
|
Event Fragments
{eventMainLayout}
|
mainEvent Fragment------------------------------------------
| | |
dateFragment fightFragment otherFragment
| | |
|
reflectionFragment
|
eventAddedFragment
|
LandingPage
|

(same activities as shown in this picture)
Repo Links
Github link: https://github.com/Mechiri/pervasive-computing/tree/working (Branch: Working)
Commit: afc04dff3553b28d06b0b4f897cb4dd804983b4f
