package com.deliguoo.ddsapp.vo.content;

import java.util.Date;

public class Note {
	// The question/answer field
	private String n;
	// The user field who created the note
	private String u;
	// The date field that this note was created
	private Date d;
	public Note() {}
	public Note(String n) {
		this.n = n;
	}
	public String getQ() {
		return n;
	}

	public void setQ(String n) {
		this.n = n;
	}
	public String getU() {
		return u;
	}
	public void setU(String u) {
		this.u = u;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
    @Override
    public String toString() {
        return String.format(
                "Note[note='%s', user='%s', date='%s']", n, u, d);
    }
}
