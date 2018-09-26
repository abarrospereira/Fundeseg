package br.com.app.api.util.messages;


import static br.com.app.api.util.messages.MessageLevel.ERROR;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;

	private String key;

	private String title;

	private String description;

	private MessageLevel level = ERROR;

	private Map<String, Object> parameters = new HashMap<String, Object>();

	public Message(String key) {
		this.key = key;
	}

	public Message() {
	}

	public Message(String key, String description) {
		this.key = key;
		this.description = description;
	}

	public Message(String key, String description, MessageLevel level) {
		this.key = key;
		this.description = description;
		this.level = level;
	}

	public Message withParameter(String parameterKey, Object parameterValue) {
		parameters.put(parameterKey, parameterValue);
		return this;
	}

	public Message withTitle(String titleKey) {
		title = titleKey;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public String getKey() {
		return key;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	@Deprecated
	public static Collection<Message> generateLesErrors(
			Collection<String> errorsString) {
		return errorsString.stream().map(s -> new Message(s))
				.collect(Collectors.toList());
	}

	@Deprecated
	public static List<String> generateErrorsString(
			Collection<Message> errors) {
		return errors.stream().map(Message::getKey)
				.collect(Collectors.toList());
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MessageLevel getLevel() {
		return level;
	}

	public void setLevel(MessageLevel level) {
		this.level = level;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (level != other.level)
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

}
