(ns askme.security
  (:require [clj-jwt.core :refer :all]
            [clj-jwt.key :refer [private-key]]
            [clj-time.core :refer [now plus days]]))

(def ^private DEFAULT_SECRET "This is just a demo secret")

(def claim
  {:iss "AskME"
   :exp (plus (now) (days 1))
   :iat (now)})



(defn new-token [data]
  (-> claim (assoc :data data) jwt (sign :HS256 DEFAULT_SECRET) to-str))

(defn verify-token [token]
  (-> token str->jwt (verify DEFAULT_SECRET)))
